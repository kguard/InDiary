package com.kguard.indiary.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme

// 탭 바에 아이템 하나
@Composable
fun IndiaryTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = {
            val style = MaterialTheme.typography.labelLarge.copy(textAlign = TextAlign.Center)
            ProvideTextStyle(value = style,
                content = {
                    text()
                }
            )
        }
    )
}

// 탭 전체
@Composable
fun IndiaryTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit,
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onSurface,
        indicator =  { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                height = 2.dp,
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        tabs = tabs
    )
}

@ThemePreviews
@Preview(showBackground = true)
@Composable
fun IndiaryTabPrev() {
    IndiaryTheme {
        val title = listOf("특징", "추억")
        IndiaryTabRow(selectedTabIndex = 0) {
            title.forEachIndexed { index, title ->
                IndiaryTab(
                    selected = index == 0,
                    onClick = { },
                    text = { Text(text = title) },
                )
            }

        }
    }
}