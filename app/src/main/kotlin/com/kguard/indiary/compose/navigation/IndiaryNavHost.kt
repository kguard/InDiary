package com.kguard.indiary.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.kguard.indiary.compose.ui.IndiaryAppState
import com.kguard.indiary.feature.person.navigation.personMainRoute

@Composable
fun IndiaryNavHost(
    appState: IndiaryAppState,
    modifier: Modifier = Modifier,
    startDestination: String = personMainRoute
){
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){

    }
}