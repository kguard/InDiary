package com.kguard.indiary.feature.person.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kguard.indiary.feature.person.screen.PersonEditRoute
import com.kguard.indiary.feature.person.screen.PersonMainRoute

const val personMainRoute = "person_main_rout"
const val PERSON_ID = "personId"
const val personDetailRoute = "person_detail_rout/{$PERSON_ID}"
const val personEditRoute = "person_edit_rout/{$PERSON_ID}"

fun NavController.navigateToPersonMain(navOptions: NavOptions? = null) {
    this.navigate(personMainRoute,navOptions)
}
fun NavController.navigateToPersonDetail(navOptions: NavOptions? = null) {
    this.navigate(personDetailRoute,navOptions)
}
fun NavController.navigateToPersonEdit(navOptions: NavOptions? = null) {
    this.navigate(personEditRoute,navOptions)
}

fun NavGraphBuilder.personMainScreen(onCardClick: (Int) -> Unit, onAddClick: () -> Unit){
    composable(
        route = personMainRoute
    ){
        PersonMainRoute(onCardClick = onCardClick, onAddClick = onAddClick)
    }
}

fun NavGraphBuilder.personEditScreen(onCompleteClick: () -> Unit){
    composable(
        route = personEditRoute,
        arguments = listOf(navArgument(PERSON_ID) {type = NavType.StringType},)
    ){
        PersonEditRoute(onCompleteClick = onCompleteClick)
    }
}