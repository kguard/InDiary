package com.kguard.indiary.compose

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.runtime.Composable
import androidx.core.animation.doOnEnd
import com.kguard.indiary.compose.ui.IndiaryApp
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val splashScreen = installSplashScreen()
//
//        splashScreen.setOnExitAnimationListener { splashScreenProvider ->
//            val fadeOut = ObjectAnimator.ofFloat(splashScreenProvider.view, View.ALPHA, 0f)
//            fadeOut.duration = 250L
//            fadeOut.doOnEnd { splashScreenProvider.remove() }
//            fadeOut.start()
//        }

        super.onCreate(savedInstanceState)
        setContent {
            IndiaryTheme {
                IndiaryApp(windowSizeClass = calculateWindowSizeClass(this) )
            }
        }
    }
}