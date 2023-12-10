package com.kguard.indiary.core.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme

@Composable
fun IndiaryFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    content: @Composable () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        shape = FloatingActionButtonDefaults.largeShape,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        elevation = elevation,
        content = content
    )
}

@ThemePreviews
@Composable
fun IndiaryFloatingActionButtonPrev() {
    IndiaryTheme {
        IndiaryFloatingActionButton(onClick = {})
        {
//            Icon(painter = painterResource(id = R.drawable.ic_memory_2_line), contentDescription ="췍" )
            Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
        }
    }
}