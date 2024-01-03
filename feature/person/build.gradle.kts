plugins {
    alias(libs.plugins.kguard.android.feature)
    alias(libs.plugins.kguard.android.library.compose)
}

android {
    namespace = "com.kguard.indiary.feature.person"
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(projects.feature.memory)

}