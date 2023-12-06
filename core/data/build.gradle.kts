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
    implementation(projects.core.database)
    implementation(projects.core.model)

    implementation (libs.androidx.core.ktx)
    testImplementation (libs.junit4)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso.core)

    implementation (libs.gson)

    //Coroutines
    implementation (libs.kotlinx.coroutines.android )

    //Lifecycle Scopes
    implementation (libs.androidx.lifecycle.livedata.ktx )
    implementation (libs.androidx.lifecycle.runtime.ktx )
    implementation (libs.androidx.lifecycle.viewmodel.ktx )

}