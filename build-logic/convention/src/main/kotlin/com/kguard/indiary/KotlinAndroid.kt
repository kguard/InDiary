package com.kguard.indiary

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * kotlin 으로 구성하는 Android 옵션
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>,
){
    commonExtension.apply {
        compileSdk =34
        defaultConfig{
            minSdk = 26
        }
        compileOptions {
//            Android 스튜디오에는 앱의 최소 API 수준 없이도 여러 자바 11+ API를 사용할 수 있도록 지원하는 기능이 포함되어 있습니다.
//            즉, Android 13(API 수준 33)에서 도입된 API를 사용해도 모든 이전 버전에서 코드가 작동합니다.
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
//            isCoreLibraryDesugaringEnabled = true
        }
    }
    configureKotlin()
//    desugaring 추가 하는 부분
//    dependencies {
//        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
//    }
}
/**
 * JVM(Non-Android)에 대한 기본 Kotlin 옵션 구성
 */
internal fun Project.configureKotlinJvm(){
    extensions.configure<JavaPluginExtension>{
//      Android 스튜디오에는 앱의 최소 API 수준 없이도 여러 자바 11+ API를 사용할 수 있도록 지원하는 기능이 포함되어 있습니다.
//      즉, Android 13(API 수준 33)에서 도입된 API를 사용해도 모든 이전 버전에서 코드가 작동합니다.
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    configureKotlin()
}
/**
 * 기본 kotlin 옵션
 */
private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach{
        kotlinOptions{
            jvmTarget = JavaVersion.VERSION_17.toString()

//            모든 Kotlin 경고를 오류로 처리(기본적으로 비활성화)
//            warningsAsErrors= true를 ~/.gradle/gradle.properties로 설정하여 재정의합니다
//            val warningsAsErrors: String? by project
//            allWarningsAsErrors = warningsAsErrors.toBoolean()
//            freeCompilerArgs = freeCompilerArgs + listOf(
//                "-opt-in=kotlin.RequiresOptIn",
//                // Enable experimental coroutines APIs, including Flow
//                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
//                "-opt-in=kotlinx.coroutines.FlowPreview",
//            )
        }
    }
}