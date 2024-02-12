package com.kguard.indiary.compose.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.kguard.indiary.R
import com.kguard.indiary.compose.navigation.IndiaryNavHost
import com.kguard.indiary.compose.navigation.TopLevelDestination
import com.kguard.indiary.core.designsystem.component.IndiaryMainTopAppBar
import com.kguard.indiary.core.designsystem.component.IndiaryNavigationBar
import com.kguard.indiary.core.designsystem.component.IndiaryNavigationBarItem


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun IndiaryApp(
    windowSizeClass: WindowSizeClass,
    appState: IndiaryAppState = rememberIndiaryAppState(windowSizeClass = windowSizeClass)
) {
    val context = LocalContext.current
    var showQuitDialog by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                IndiaryBottomBar(
                    modifier = Modifier.height(100.dp),
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        }
    )
    { padding ->
        Column(Modifier.fillMaxSize()) {
            val destination = appState.currentTopLevelDestination
            if (destination != null) {
                when (destination) {
                    TopLevelDestination.PERSON -> {
                        IndiaryMainTopAppBar(actionIcon = R.drawable.ic_person_add,
                            onLongClick = {
                                Intent(context, OssLicensesMenuActivity::class.java).also {
                                    OssLicensesMenuActivity.setActivityTitle("오픈소스 라이선스")
                                    context.startActivity(it)
                                }
                            },
                            onNavigationClick = { appState.navigateToPersonAdd() })
                    }

                    TopLevelDestination.MEMORY -> {
                        IndiaryMainTopAppBar(actionIcon = R.drawable.ic_memory_add,
                            onNavigationClick = { appState.navigateToMemoryAdd() })
                    }
                }
            }
            IndiaryNavHost(modifier = Modifier, appState = appState)
        }

    }
}

@Composable
private fun IndiaryBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    IndiaryNavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            IndiaryNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(destination.unselectedIcon),
                        contentDescription = null
                    )
                },
                selectedIcon = {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(destination.selectedIcon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(destination.iconTextId),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        maxLines = 1
                    )
                },
                alwaysShowLabel = false
            )
        }
    }

}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
