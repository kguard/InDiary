package com.kguard.indiary.feature.memory.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kguard.indiary.feature.memory.screen.MemoryMainRoute

const val memoryMainRoute = "memory_main_rout"
const val memoryDetailRoute = "memory_detail_rout"
const val personEditRoute = "person_edit_rout"

fun NavController.navigateToMemoryMain(navOptions: NavOptions? = null) {
    this.navigate(memoryMainRoute,navOptions)
}
fun NavController.navigateToMemoryDetail(navOptions: NavOptions? = null) {
    this.navigate(memoryDetailRoute,navOptions)
}
fun NavController.navigateToMemoryEdit(navOptions: NavOptions? = null) {
    this.navigate(personEditRoute,navOptions)
}
fun NavGraphBuilder.memoryMainScreen(onCardClick: (Int) -> Unit, onAddClick: () -> Unit){
    composable(
        route = memoryMainRoute
    ){
        MemoryMainRoute(onCardClick = onCardClick, onAddClick = onAddClick)
    }
}
