package com.kguard.indiary.compose.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.kguard.indiary.compose.ui.IndiaryAppState
import com.kguard.indiary.compose.ui.splash
import com.kguard.indiary.compose.ui.splashScreen
import com.kguard.indiary.feature.memory.navigation.memoryAddScreen
import com.kguard.indiary.feature.memory.navigation.memoryDetailScreen
import com.kguard.indiary.feature.memory.navigation.memoryMainScreen
import com.kguard.indiary.feature.memory.navigation.memoryUpdateScreen
import com.kguard.indiary.feature.memory.navigation.navigateToMemoryDetail
import com.kguard.indiary.feature.memory.navigation.navigateToMemoryUpdate
import com.kguard.indiary.feature.person.navigation.navigateToPersonDetail
import com.kguard.indiary.feature.person.navigation.navigateToPersonUpdate
import com.kguard.indiary.feature.person.navigation.personAddScreen
import com.kguard.indiary.feature.person.navigation.personDetailScreen
import com.kguard.indiary.feature.person.navigation.personMainScreen
import com.kguard.indiary.feature.person.navigation.personUpdateScreen

@Composable
fun IndiaryNavHost(
    appState: IndiaryAppState,
    modifier: Modifier = Modifier,
    startDestination: String = splash
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        splashScreen(navController)
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
            navController = navController,
            onCompleteClick = navController::popBackStack
        )
        memoryAddScreen(
            onCompleteClick = navController::popBackStack
        )
    }
}