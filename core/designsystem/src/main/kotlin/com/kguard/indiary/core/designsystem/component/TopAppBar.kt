package com.kguard.indiary.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndiarySubTopAppBar(
    @StringRes titleRes: Int,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String?,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleRes),
                style = MaterialTheme.typography.titleMedium,
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = colors,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndiaryMainTopAppBar(
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.AppNamePage),
                style = MaterialTheme.typography.titleMedium,
            )
        },
        actions = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
fun IndiaryTopAppBarPrev() {
    IndiaryTheme {
        IndiarySubTopAppBar(
            titleRes = R.string.AddPersonPage,
            navigationIcon = Icons.Rounded.ArrowBack,
            navigationIconContentDescription = "Back Button"
        )
        IndiaryMainTopAppBar(
            actionIcon = Icons.Rounded.ArrowBack,
            actionIconContentDescription = "Back Button"
        )
    }
}