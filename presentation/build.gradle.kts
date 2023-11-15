plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
   // id("com.google.android.gms.oss-licenses-plugin")
}

android {
    namespace = "com.kguard.indiary"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kguard.indiary"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    dataBinding{

    }
}

dependencies {

    //implementation ("androidx.core:core-ktx:1.12.0")
    implementation(libs.androidx.core.ktx)
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    //implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    //CA
    implementation (project (":domain"))
    implementation (project (":data"))

    //glide
    val glide_version ="4.16.0"
    implementation ("com.github.bumptech.glide:glide:$glide_version")
    annotationProcessor ("com.github.bumptech.glide:compiler:$glide_version")
    kapt( "com.github.bumptech.glide:compiler:$glide_version")

    //Room DB
    val room_version ="2.6.0"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")

    //Lottie
    val lottie_version ="6.1.0"
    implementation ("com.airbnb.android:lottie:$lottie_version")

//    //android paging3
//    def paging_version = "3.2.1"
//    implementation"androidx.paging:paging-runtime:$paging_version"

    //Coroutines
    val coroutines_version = "1.6.4"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    // Lifecycle Scopes
    val lifecycle_version = "2.6.1"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    //TedPremission
    val ted_version ="3.3.0"
    implementation ("io.github.ParkSangGwon:tedpermission-normal:$ted_version")
    implementation ("io.github.ParkSangGwon:tedpermission-coroutine:$ted_version")

    //Navigation Component
    val nav_version = "2.6.0"
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation ("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.46.1")
    kapt ("com.google.dagger:hilt-compiler:2.46.1")
    kapt ("com.google.dagger:hilt-android-compiler:2.46.1")
    //implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    //kapt ("androidx.hilt:hilt-compiler:1.1.0")


    implementation ("com.google.android.gms:play-services-oss-licenses:17.0.0")

    implementation ("com.google.code.gson:gson:2.9.1")

    implementation ("com.github.esafirm:android-image-picker:3.0.0-beta5")

}