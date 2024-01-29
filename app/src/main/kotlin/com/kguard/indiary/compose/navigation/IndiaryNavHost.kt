package com.kguard.indiary.compose.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.kguard.indiary.compose.ui.IndiaryAppState
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.feature.memory.navigation.memoryAddScreen
import com.kguard.indiary.feature.memory.navigation.memoryDetailScreen
import com.kguard.indiary.feature.memory.navigation.memoryMainScreen
import com.kguard.indiary.feature.memory.navigation.memoryUpdateScreen
import com.kguard.indiary.feature.memory.navigation.navigateToMemoryAdd
import com.kguard.indiary.feature.memory.navigation.navigateToMemoryDetail
import com.kguard.indiary.feature.memory.navigation.navigateToMemoryUpdate
import com.kguard.indiary.feature.person.navigation.navigateToPersonAdd
import com.kguard.indiary.feature.person.navigation.navigateToPersonDetail
import com.kguard.indiary.feature.person.navigation.navigateToPersonUpdate
import com.kguard.indiary.feature.person.navigation.personAddScreen
import com.kguard.indiary.feature.person.navigation.personDetailRoute
import com.kguard.indiary.feature.person.navigation.personDetailScreen
import com.kguard.indiary.feature.person.navigation.personMainRoute
import com.kguard.indiary.feature.person.navigation.personMainScreen
import com.kguard.indiary.feature.person.navigation.personUpdateRoute
import com.kguard.indiary.feature.person.navigation.personUpdateScreen
import com.kguard.indiary.feature.person.screen.PersonUpdateRoute

@Composable
fun IndiaryNavHost(
    appState: IndiaryAppState,
    modifier: Modifier = Modifier,
    startDestination: String = personMainRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
//        enterTransition = {
//            fadeIn(
//                animationSpec = tween(durationMillis = 300),
//                initialAlpha = 0.99f
//            )
//        },
//        exitTransition = {
//            fadeOut(
//                animationSpec = tween(durationMillis = 300),
//                targetAlpha = 0.99f
//            )
//        }
    ) {
        personMainScreen(
            onCardClick = navController::navigateToPersonDetail,
        )
        personDetailScreen(
            onCardClick = navController::navigateToMemoryDetail,
            onUpdateClick = navController::navigateToPersonUpdate,
            onBackClick = navController::popBackStack,
            onDeleteClick = navController::popBackStack,
        )
        personAddScreen(
            onCompleteClick = navController::popBackStack,
        )
        personUpdateScreen(
            navController = navController,
            onCompleteClick = navController::popBackStack,
        )
//        composable(
//            route = personUpdateRoute,
//            arguments = listOf(navArgument(PERSON) { type = NavType.StringType })
//        ) { navBackStackEntry ->
//            val parents = remember(navBackStackEntry) {
//                navController.getBackStackEntry(personDetailRoute)
//            }
//            val personJson = navBackStackEntry.arguments?.getString(PERSON)
//            val person = Gson().fromJson(personJson, DomainPerson::class.java)
//            PersonUpdateRoute(
//                onCompleteClick = navController::popBackStack,
//                person = person,
//                personDetailViewModel = hiltViewModel(parents)
//            )
//        }
        memoryMainScreen(
            onCardClick = navController::navigateToMemoryDetail,
        )
        memoryDetailScreen(
            onUpdateClick = navController::navigateToMemoryUpdate,
            onDeleteClick = navController::popBackStack,
            onBackClick = navController::popBackStack
        )
        memoryUpdateScreen(
            onCompleteClick = navController::popBackStack
        )
        memoryAddScreen(
            onCompleteClick = navController::popBackStack
        )
    }
}