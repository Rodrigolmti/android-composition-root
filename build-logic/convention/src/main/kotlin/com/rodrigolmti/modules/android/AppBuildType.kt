package com.rodrigolmti.modules.android

@Suppress("unused")
enum class AppBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
    BENCHMARK(".benchmark")
}
