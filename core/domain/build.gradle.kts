plugins {
   // alias(libs.plugins.kguard.jvm.library)
    alias(libs.plugins.kguard.android.library)
    alias(libs.plugins.kguard.android.hilt)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.kguard.core.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation (libs.kotlinx.coroutines.android )

}