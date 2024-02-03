package com.kguard.indiary.feature.memory.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kguard.indiary.feature.memory.screen.MemoryAddRoute
import com.kguard.indiary.feature.memory.screen.MemoryDetailRoute
import com.kguard.indiary.feature.memory.screen.MemoryUpdateRoute
import com.kguard.indiary.feature.memory.screen.MemoryMainRoute

const val MEMORY_ID = "memoryId"
const val memoryMainRoute = "memory_main_rout"
const val memoryAddRoute = "memory_add_rout"
const val memoryDetailRoute = "memory_detail_rout/{$MEMORY_ID}"
const val memoryUpdateRoute = "memory_update_rout"

fun NavHostController.navigateToMemoryMain(navOptions: NavOptions? = null) {
    this.navigate(memoryMainRoute, navOptions)
}
fun NavHostController.navigateToMemoryAdd(navOptions: NavOptions? = null) {
    this.navigate(memoryAddRoute, navOptions)
}

fun NavHostController.navigateToMemoryDetail(memoryId : Int, navOptions: NavOptions? = null) {
    this.navigate("memory_detail_rout/$memoryId", navOptions)
}

fun NavHostController.navigateToMemoryUpdate(navOptions: NavOptions? = null) {
    this.navigate("memory_update_rout", navOptions)
}

fun NavGraphBuilder.memoryMainScreen(onCardClick: (Int) -> Unit) {
    composable(
        route = memoryMainRoute
    ) {
        MemoryMainRoute(onCardClick = onCardClick)
    }
}

fun NavGraphBuilder.memoryAddScreen(onCompleteClick: () -> Unit) {
    composable(
        route = memoryAddRoute
    ) {
        MemoryAddRoute(onCompleteClick = onCompleteClick,)
    }
}


fun NavGraphBuilder.memoryDetailScreen(onUpdateClick: () -> Unit, onDeleteClick: () -> Unit, onBackClick: () -> Unit) {
    composable(
        route = memoryDetailRoute,
        arguments = listOf(navArgument(MEMORY_ID) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val memoryId = navBackStackEntry.arguments?.getInt(MEMORY_ID)
        MemoryDetailRoute(
            onUpdateClick = onUpdateClick,
            onDeleteClick = onDeleteClick,
            onBackClick = onBackClick,
            memoryId = memoryId!!
        )
    }
}

fun NavGraphBuilder.memoryUpdateScreen(navController: NavController, onCompleteClick: () -> Unit) {
    composable(
        route = memoryUpdateRoute,
    )
    { navBackStackEntry ->
        val parents = remember(navBackStackEntry) {
            navController.getBackStackEntry(memoryDetailRoute)
        }
        MemoryUpdateRoute(
            onCompleteClick = onCompleteClick,
            memoryDetailViewModel = hiltViewModel(parents)
        )
    }
}