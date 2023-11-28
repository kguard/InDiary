plugins {
    alias(libs.plugins.kguard.android.library)
    alias(libs.plugins.kguard.android.hilt)
}

android {
    namespace = "com.kguard.core.common"
}

dependencies {

    implementation(libs.kotlinx.coroutines.android)

}