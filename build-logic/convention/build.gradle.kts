import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.rodrigolmti.modules.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
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
