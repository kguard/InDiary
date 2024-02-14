package com.kguard.indiary.core.designsystem.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



private val LightColorScheme = lightColorScheme(
    primary = brown20Sub,
    onPrimary = Color.White,
    primaryContainer = brown10Third,
    onPrimaryContainer = brown30Main,
    onSurface = brown30Main,
    onSurfaceVariant = gray,
    background = beige20Main,
    onBackground = brown20Sub,
    outline = yellow,
    error = Color.Red,
    secondary = beige20Main,
    onSecondary = brown30Main,
    secondaryContainer = beige10Sub,
    onSecondaryContainer = beige30Deep
)

private val DarkColorScheme = darkColorScheme(
    primary = brown20Sub,
    onPrimary = Color.White,
    onPrimaryContainer = brown20Sub,
    background = brown10Third,
    onBackground = brown30Main
)

@Composable
fun IndiaryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && supportsDynamicTheming() -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = Color.Transparent.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = IndiaryTypography,
        content =  content
    )
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
