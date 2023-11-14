plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.kguard.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    implementation(project (":domain"))

    // Room
    implementation ("androidx.room:room-runtime:2.6.0")
    kapt ("androidx.room:room-compiler:2.6.0")
    // Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.6.0")
    implementation ("com.google.code.gson:gson:2.9.1")

    //Coroutines
    val coroutines_version = "1.6.4"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    //Coroutine Lifecycle Scopes
    val lifecycle_version = "2.6.1"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    //LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.46.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.46.1")
    kapt ("com.google.dagger:hilt-compiler:2.46.1")
    //implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    //kapt "androidx.hilt:hilt-compiler:1.1.0"
}