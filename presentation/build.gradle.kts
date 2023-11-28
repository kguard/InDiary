plugins {
//    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.kguard.android.application)
    id("androidx.navigation.safeargs")
    id("com.google.android.gms.oss-licenses-plugin")
    alias(libs.plugins.kguard.android.hilt)
    alias(libs.plugins.kguard.android.room)
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
       // compose = true
    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.2"
//    }

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

    implementation(libs.androidx.core.ktx)
    implementation (libs.androidx.appcompat)

    //compose를 사용하게 되면 사용을 안할 것들
    implementation (libs.android.material)
    implementation (libs.androidx.constraintlayout)

    testImplementation (libs.junit4)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso.core)

    //CA
    implementation (project (":domain"))
    implementation (project (":data"))

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

}