plugins {
//    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.kguard.android.application)
    alias(libs.plugins.kguard.android.application.compose)
    alias(libs.plugins.kguard.android.hilt)
    alias(libs.plugins.kguard.android.room)
    id("androidx.navigation.safeargs")
    id("com.google.android.gms.oss-licenses-plugin")
    //    id("kotlin-kapt")
}

android {
    namespace = "com.kguard.indiary"
    defaultConfig {
        applicationId = "com.kguard.indiary"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
        buildConfig = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.feature.person)
    implementation(projects.feature.memory)

    implementation(libs.androidx.core.ktx)
    implementation (libs.androidx.appcompat)

    //compose를 사용하게 되면 사용을 안할 것들
    implementation (libs.android.material)
    implementation (libs.androidx.constraintlayout)

    testImplementation (libs.junit4)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso.core)

    //CA
//    implementation (project (":core:domain"))
//    implementation (project (":core:data"))
    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.database)

    //glide
    implementation (libs.glide.glide)

    //Lottie
    implementation (libs.lottie)

    //Coroutine
    implementation (libs.kotlinx.coroutines.android )

    // Lifecycle Scopes
    implementation (libs.androidx.lifecycle.livedata.ktx )
    implementation (libs.androidx.lifecycle.runtime.ktx )
    implementation (libs.androidx.lifecycle.viewmodel.ktx )

    //TedPremission
    implementation (libs.tedpermission.normal)
    implementation (libs.tedpermission.coroutine)

    //Navigation Component
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)

    implementation(libs.google.oss.licenses)

    implementation (libs.gson)

    implementation (libs.android.image.picker)
    //compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

}