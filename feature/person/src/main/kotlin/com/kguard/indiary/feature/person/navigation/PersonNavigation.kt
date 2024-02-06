package com.kguard.indiary.feature.person.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kguard.indiary.feature.person.screen.PersonAddRoute
import com.kguard.indiary.feature.person.screen.PersonDetailRoute
import com.kguard.indiary.feature.person.screen.PersonMainRoute
import com.kguard.indiary.feature.person.screen.PersonUpdateRoute

const val PERSON_ID = "personId"
const val personMainRoute = "person_main_rout"
const val personAddRoute = "person_add_rout"
const val personDetailRoute = "person_detail_rout/{$PERSON_ID}"
const val personUpdateRoute = "person_update_rout"

fun NavHostController.navigateToPersonMain(navOptions: NavOptions? = null) {
    this.navigate(personMainRoute, navOptions)
}
fun NavHostController.navigateToPersonAdd(navOptions: NavOptions? = null) {
    this.navigate(personAddRoute, navOptions)
}

fun NavHostController.navigateToPersonDetail(personId: Int, navOptions: NavOptions? = null) {
    this.navigate( "person_detail_rout/$personId", navOptions)
}

fun NavHostController.navigateToPersonUpdate(navOptions: NavOptions? = null) {
    this.navigate("person_update_rout", navOptions)
}

fun NavGraphBuilder.personMainScreen(onCardClick: (Int) -> Unit) {
    composable(
        route = personMainRoute
    ) {
        PersonMainRoute(onCardClick = onCardClick)
    }
}
fun NavGraphBuilder.personAddScreen(onCompleteClick: () -> Unit) {
    composable(
        route = personAddRoute
    ) {
        PersonAddRoute(onCompleteClick = onCompleteClick)
    }
}

fun NavGraphBuilder.personUpdateScreen(navController: NavController,onCompleteClick: () -> Unit) {
    composable(
        route = personUpdateRoute,
    ) { navBackStackEntry ->
        val parents = remember(navBackStackEntry) {
                navController.getBackStackEntry(personDetailRoute)
            }
        PersonUpdateRoute(
            onCompleteClick = onCompleteClick,
            personDetailViewModel = hiltViewModel(parents)
        )
    }
}

fun NavGraphBuilder.personDetailScreen(
    onUpdateClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
    onBackClick: () -> Unit
) {
    composable(
        route = personDetailRoute,
        arguments = listOf(navArgument(PERSON_ID) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val personId = navBackStackEntry.arguments?.getInt(PERSON_ID)
        PersonDetailRoute(
            onUpdateClick = onUpdateClick,
            onCardClick = onCardClick,
            onDeleteClick = onDeleteClick,
            onBackClick = onBackClick,
            personId = personId!!
        )
    }

}