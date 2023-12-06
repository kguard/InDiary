plugins {
    alias(libs.plugins.kguard.android.library)
    alias(libs.plugins.kguard.android.hilt)
//    id("kotlinx-serialization")
}

android {
    namespace = "com.kguard.indiary.core.network"
    buildFeatures{
        buildConfig = true
    }
}

dependencies {

}