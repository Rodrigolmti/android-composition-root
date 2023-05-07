plugins {
    id("rodrigolmti.android.application")
}

android {
    namespace = "com.rodrigolmti.modules"

    defaultConfig {
        applicationId = "com.rodrigolmti.modules"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    debugImplementation(libs.bundles.compose.debug)
}