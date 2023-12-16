plugins {
    alias(libs.plugins.kguard.android.feature)
}

android {
    namespace = "com.kguard.indiary.feature.memory"
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)

}