@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kguard.android.library)
    alias(libs.plugins.kguard.android.hilt)
    alias(libs.plugins.kguard.android.room)
}

android {
    namespace = "com.kguard.core.database"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}