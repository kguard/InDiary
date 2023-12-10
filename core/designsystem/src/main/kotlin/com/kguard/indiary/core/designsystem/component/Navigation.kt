package com.kguard.indiary.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme

//네비게이션 아이템 하나의 속성
@Composable
fun RowScope.IndiaryNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = IndiaryNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = IndiaryNavigationDefaults.navigationContentColor(),
            selectedTextColor = IndiaryNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = IndiaryNavigationDefaults.navigationContentColor(),
            indicatorColor = IndiaryNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

// 네비게이션 바
@Composable
fun IndiaryNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = IndiaryNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}

//@ThemePreviews
@ThemePreviews
@Composable
fun IndiaryNavigationBarPreview() {
    val items = listOf("사람", "추억")
    val icons = listOf(
        R.drawable.ic_person_2_line,
        R.drawable.ic_memory_2_line
    )
    val selectedIcons = listOf(
        R.drawable.ic_person_2_fill,
        R.drawable.ic_memory_2_fill
    )
    IndiaryTheme {
        IndiaryNavigationBar {
            items.forEachIndexed { index, item ->
                IndiaryNavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(id = selectedIcons[index]),
                            contentDescription = item,
                        )
                    },
                    label = { Text(item, maxLines = 1) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}


// 네비게이션 아이콘 및 글씨에 쓰이는 색상
object IndiaryNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    // 인디케이터 삭제를 위하여 surface 색상과 동일 하게 사용
    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.surface


}
