//buildscript {
//    dependencies {
//        classpath "com.google.dagger:hilt-android-gradle-plugin:2.40.5"
//        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.5.1"
//        classpath 'com.google.android.gms:oss-licenses-plugin:0.10.4'
//    }
//}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id ("com.android.application") version "8.1.3" apply false
    id ("com.android.library") version "8.1.3" apply false
    id ("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("androidx.navigation.safeargs.kotlin") version "2.6.0" apply false
    id ("com.google.dagger.hilt.android") version "2.46.1" apply false
    id ("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    //id ("com.google.android.gms.oss-licenses-plugin") version "0.10.6" apply false
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
