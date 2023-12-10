package com.kguard.indiary.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme

// 일반 컨텐츠 슬롯이 있는 버튼
@Composable
fun IndiaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        contentPadding = contentPadding,
        content = content
    )
}

// 텍스트 및 아이콘 콘텐츠 슬록으로 채워진 버튼
@Composable
fun IndiaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    IndiaryButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = if (leadingIcon != null) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            ButtonDefaults.ContentPadding
        },
    ) {
        IndiaryButtonContent(
            text = text,
            leadingIcon = leadingIcon
        )
    }
}

@Composable
fun IndiaryTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        content = content
    )
}

@Composable
fun IndiaryTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    IndiaryTextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        IndiaryButtonContent(
            text = text,
            leadingIcon = leadingIcon
        )
    }
}

@Composable
private fun IndiaryButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    if (leadingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            leadingIcon()
        }
    }
    Box(
        Modifier
            .padding(
                start = if (leadingIcon != null) {
                    ButtonDefaults.IconSpacing
                } else {
                    0.dp
                },
            ),
    ) {
        text()
    }
}


@Composable
@ThemePreviews
fun IndiaryButtonPrev() {
    IndiaryTheme {
        IndiaryButton(onClick = {}, text = { Text(text = "TEST BUTTON") }) {
        }
    }
}

@Composable
@ThemePreviews
fun IndiaryTextButtonPrev() {
    IndiaryTheme {
        IndiaryTextButton(onClick = {}, text = { Text(text = "TEST BUTTON") }) {
        }
    }
}