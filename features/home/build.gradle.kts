@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("rodrigolmti.android.feature")
    id("rodrigolmti.android.compose")
    id("kotlinx-serialization")
}

android {
    namespace = "com.rodrigolmti.modules.home"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.android)
}