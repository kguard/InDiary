package com.kguard.indiary.compose.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


@Composable
fun IndiaryApp(
    windowSizeClass: WindowSizeClass,
    appState: IndiaryAppState = rememberIndiaryAppState(windowSizeClass = windowSizeClass)
) {
    var showDialog by rememberSaveable {
        mutableStateOf(false)
    }
}