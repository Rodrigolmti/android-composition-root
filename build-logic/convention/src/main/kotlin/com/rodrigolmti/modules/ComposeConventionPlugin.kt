import com.android.build.gradle.LibraryExtension
import com.rodrigolmti.modules.android.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class ComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.withPlugin("com.android.library") {
                val extension = extensions.getByType<LibraryExtension>()
                configureAndroidCompose(extension)
            }
        }
    }
}