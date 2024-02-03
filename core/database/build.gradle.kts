plugins {
    alias(libs.plugins.kguard.android.library)
    alias(libs.plugins.kguard.android.hilt)
    alias(libs.plugins.kguard.android.room)
}

android {
    namespace = "com.kguard.indiary.core.database"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.gson)
}