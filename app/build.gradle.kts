import com.rodrigolmti.modules.android.AppBuildType

plugins {
    id("rodrigolmti.android.application")
    alias(libs.plugins.android.application)
    alias(libs.plugins.androidx.baselineprofile)
}

android {
    namespace = "com.rodrigolmti.modules"

    defaultConfig {
        applicationId = "com.rodrigolmti.modules"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        debug {
            applicationIdSuffix = AppBuildType.DEBUG.applicationIdSuffix
        }
        val release by getting {
            isMinifyEnabled = true
            isShrinkResources = true
            applicationIdSuffix = AppBuildType.RELEASE.applicationIdSuffix
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
        }
        create("benchmark") {
            initWith(release)
            matchingFallbacks.add("release")
            proguardFiles("benchmark-rules.pro")
            isMinifyEnabled = true
            applicationIdSuffix = AppBuildType.BENCHMARK.applicationIdSuffix
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":core:ui-kit"))
    implementation(project(":core:navigation"))

    implementation(project(":features:home"))
    implementation(project(":features:orders"))
    implementation(project(":features:profile"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.profileinstaller)
    "baselineProfile"(project(mapOf("path" to ":baselineprofile")))

    debugImplementation(libs.bundles.compose.debug)
}