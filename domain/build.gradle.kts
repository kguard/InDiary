plugins {
    id ("kotlin-kapt")
    id ("org.jetbrains.kotlin.jvm")
}
//
//android {
//    compileSdk 32
//
//    defaultConfig {
//        minSdk 26
//        targetSdk 32
//
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles "consumer-rules.pro"
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = '1.8'
//    }
//}

dependencies {

    implementation (libs.kotlinx.coroutines.android )
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
//    implementation 'androidx.core:core-ktx:1.8.0'
//    implementation 'androidx.appcompat:appcompat:1.5.0'
//    implementation 'com.google.android.material:material:1.6.1'
//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

}