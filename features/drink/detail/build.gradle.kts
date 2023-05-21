@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("rodrigolmti.android.feature")
    id("rodrigolmti.android.compose")
}

android {
    namespace = "com.rodrigolmti.modules.drink.detail"
}

dependencies {
    implementation(project(":core:network"))

    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.android)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
}