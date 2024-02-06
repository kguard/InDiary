package com.kguard.indiary.core.designsystem.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun IndiaryPhoto(photo: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = photo,
        contentDescription = null,
        modifier = modifier
            .padding(end = 8.dp)
            .height(80.dp)
            .width(60.dp)
            .clip(
                RoundedCornerShape(5.dp)
            ),
    )
}
