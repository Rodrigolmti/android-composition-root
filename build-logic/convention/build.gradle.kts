plugins {
    `kotlin-dsl`
}

group = "com.rodrigolmti.modules.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "rodrigolmti.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "rodrigolmti.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "rodrigolmti.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("composeLibrary") {
            id = "rodrigolmti.android.compose"
            implementationClass = "ComposeConventionPlugin"
        }
    }
}
