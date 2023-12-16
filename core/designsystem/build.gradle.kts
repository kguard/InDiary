plugins {
    alias(libs.plugins.kguard.android.library)
    alias(libs.plugins.kguard.android.library.compose)
}

android {
    namespace = "com.kguard.indiary.core.designsystem"
}

dependencies {
    implementation (libs.androidx.appcompat)
    // icon 가져오기 위해서 일시적으로 사용
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)
    debugApi(libs.androidx.compose.ui.tooling)

}