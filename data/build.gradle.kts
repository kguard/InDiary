plugins {
//    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.kguard.android.library)
    alias(libs.plugins.kguard.android.hilt)
    alias(libs.plugins.kguard.android.room)
}

android {
    namespace = "com.kguard.data"
//    compileSdk = 34
//
//    defaultConfig {
//        minSdk = 26
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
//    }
//    buildFeatures {
//        buildConfig = true
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_17
//        targetCompatibility = JavaVersion.VERSION_17
//    }
//    kotlinOptions {
//        jvmTarget = "17"
//    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.appcompat)
    testImplementation (libs.junit4)
    androidTestImplementation (libs.androidx.test.ext)
    androidTestImplementation (libs.androidx.test.espresso.core)

    implementation(project (":domain"))

    implementation (libs.gson)

    //Coroutines
    implementation (libs.kotlinx.coroutines.android )

    //Lifecycle Scopes
    implementation (libs.androidx.lifecycle.livedata.ktx )
    implementation (libs.androidx.lifecycle.runtime.ktx )
    implementation (libs.androidx.lifecycle.viewmodel.ktx )

}