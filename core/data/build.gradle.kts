plugins {
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.kguard.android.library)
    alias(libs.plugins.kguard.android.hilt)
    alias(libs.plugins.kguard.android.room)
}

android {
    namespace = "com.kguard.core.data"

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.appcompat)
    testImplementation (libs.junit4)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso.core)

    implementation(project (":core:domain"))

    implementation (libs.gson)

    //Coroutines
    implementation (libs.kotlinx.coroutines.android )

    //Lifecycle Scopes
    implementation (libs.androidx.lifecycle.livedata.ktx )
    implementation (libs.androidx.lifecycle.runtime.ktx )
    implementation (libs.androidx.lifecycle.viewmodel.ktx )

}