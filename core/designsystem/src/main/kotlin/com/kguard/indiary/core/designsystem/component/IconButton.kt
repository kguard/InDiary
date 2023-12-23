package com.kguard.indiary.core.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
        containerColor = MaterialTheme.colorScheme.onSurface,
        contentColor = MaterialTheme.colorScheme.onBackground,
        elevation = elevation,
        content = content
    )
}

@Composable
fun IndiaryToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    checkedIcon: @Composable () -> Unit = icon,
) {
    IconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier.size(24.dp),
        enabled = enabled,
        colors = IconButtonDefaults.iconToggleButtonColors(
            containerColor = Color.Transparent,
            checkedContentColor = MaterialTheme.colorScheme.outline
        )
    ) {
        if (checked) checkedIcon() else icon()
    }
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

@ThemePreviews
@Composable
fun IndiaryToggleButtonPrev() {
    IndiaryTheme {
        IndiaryToggleButton(
            checked = true,
            onCheckedChange = {},
            icon ={
                  Icon(painter = painterResource(id = R.drawable.ic_star_line), contentDescription =null )
            },
            checkedIcon ={
                         Icon(painter = painterResource(id = R.drawable.ic_star_fill), contentDescription =null )
            },
        )
    }
}
@ThemePreviews
@Composable
fun IndiaryToggleButtonPrev2() {
    IndiaryTheme {
        IndiaryToggleButton(
            checked = false,
            onCheckedChange = {},
            icon ={
                Icon(painter = painterResource(id = R.drawable.ic_star_line), contentDescription =null )
            },
            checkedIcon ={
                Icon(painter = painterResource(id = R.drawable.ic_star_fill), contentDescription =null )
            },
        )
    }
}