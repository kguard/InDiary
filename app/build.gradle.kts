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
        versionCode = 4
        versionName = "1.1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        minSdk = 29
        targetSdk = 35
        signingConfig = signingConfigs.getByName("debug")
    }
    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
        buildConfig = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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

    testImplementation (libs.junit4)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso.core)

    //CA
    implementation(projects.core.model)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.common)

    //Lottie
    implementation (libs.lottie)
    implementation(libs.lottie.compose)

    //Coroutine
    implementation (libs.kotlinx.coroutines.android )

    // Lifecycle Scopes
    implementation (libs.androidx.lifecycle.livedata.ktx )
    implementation (libs.androidx.lifecycle.runtime.ktx )
    implementation (libs.androidx.lifecycle.viewmodel.ktx )

    //Navigation Component
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)

    implementation(libs.google.oss.licenses)

    implementation (libs.gson)

    //compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
}