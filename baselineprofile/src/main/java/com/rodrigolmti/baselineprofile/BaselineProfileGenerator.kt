package com.rodrigolmti.baselineprofile

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val BASIC_SAMPLE_PACKAGE = "com.rodrigolmti.modules"
private const val LAUNCH_TIMEOUT = 5000L

/**
 * This test class generates a basic startup baseline profile for the target package.
 *
 * We recommend you start with this but add important user flows to the profile to improve their performance.
 * Refer to the [baseline profile documentation](https://d.android.com/topic/performance/baselineprofiles)
 * for more information.
 *
 * You can run the generator with the Generate Baseline Profile run configuration,
 * or directly with `generateBaselineProfile` Gradle task:
 * ```
 * ./gradlew :app:generateReleaseBaselineProfile -Pandroid.testInstrumentationRunnerArguments.androidx.benchmark.enabledRules=BaselineProfile
 * ```
 * The run configuration runs the Gradle task and applies filtering to run only the generators.
 *
 * Check [documentation](https://d.android.com/topic/performance/benchmarking/macrobenchmark-instrumentation-args)
 * for more information about available instrumentation arguments.
 *
 * After you run the generator, you can verify the improvements running the [StartupBenchmarks] benchmark.
 **/
@RequiresApi(Build.VERSION_CODES.P)
@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {


    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() {
        rule.collectBaselineProfile(BASIC_SAMPLE_PACKAGE) {
            // Initialize UiDevice instance
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

            // Start from the home screen
            device.pressHome()

            // Wait for launcher
            val launcherPackage: String = device.launcherPackageName
            assertThat(launcherPackage, notNullValue())
            device.wait(
                Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT
            )

            // Launch the app
            val context = ApplicationProvider.getApplicationContext<Context>()
            val intent = context.packageManager.getLaunchIntentForPackage(
                BASIC_SAMPLE_PACKAGE
            )?.apply {
                // Clear out any previous instances
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            context.startActivity(intent)

            // Wait for the app to appear
            device.wait(
                Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT
            )
        }
    }
}