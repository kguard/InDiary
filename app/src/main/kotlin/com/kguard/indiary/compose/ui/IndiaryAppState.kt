package com.kguard.indiary.compose.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kguard.indiary.feature.person.navigation.personMainRoute
import com.kguard.indiary.compose.navigation.TopLevelDestination
import com.kguard.indiary.compose.navigation.TopLevelDestination.PERSON
import com.kguard.indiary.compose.navigation.TopLevelDestination.MEMORY
import com.kguard.indiary.feature.memory.navigation.memoryMainRoute
import com.kguard.indiary.feature.memory.navigation.navigateToMemoryMain
import com.kguard.indiary.feature.person.navigation.navigateToPersonMain


@Composable
fun rememberIndiaryAppState(
    windowSizeClass: WindowSizeClass,
//    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): IndiaryAppState {
    return remember(
        navController,
//        coroutineScope,
        windowSizeClass
    ) {
        IndiaryAppState(
            navController,
            windowSizeClass
        )
    }
}

@Stable
class IndiaryAppState(
    val navController: NavHostController,
//    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            personMainRoute -> PERSON
            memoryMainRoute -> MEMORY
            else -> null
        }
    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    /**
     * 앱에서 최상위 목적지로 이동하기 위한 UI 로직. 최상위 목적지에는 백스택의 목적지 복사본이 하나만 있으며, 당신이 그것을 오갈 때마다 상태를 저장하고 복원합니다.
     * @param topLevelDestination: 앱이 탐색해야 하는 대상입니다.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // 사용자가 항목을 선택할 때 백스택에 대규모 대상 스택을 구축하지 않도록 그래프의 시작 대상으로 팝업
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // 동일한 항목을 다시 선택할 때 동일한 대상의 여러 복사본을 피합니다.
            launchSingleTop = true
            // 이전에 선택한 항목을 다시 선택할 때 상태 복원
            restoreState = true
        }
        when (topLevelDestination) {
            PERSON -> navController.navigateToPersonMain(topLevelNavOptions)
            MEMORY -> navController.navigateToMemoryMain(topLevelNavOptions)
        }

    }
}