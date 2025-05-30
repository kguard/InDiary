package com.kguard.indiary.core.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.component.IndiaryPhoto
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.model.DomainMemory

//TODO: 사진 불러오기 안되면 고쳐야됨
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryCard(
    onCardClick: (Int) -> Unit,
    memory: DomainMemory,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {onCardClick(memory.memoryId)},
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = Color.White
        ),
        modifier = Modifier
            .padding(8.dp)
            .border(3.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20)),
        shape = RoundedCornerShape(20),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = memory.title, modifier.padding(top = 16.dp, start = 16.dp))
            Text(
                text = memory.date,
                modifier.padding(start = 16.dp, top = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            if (memory.imageList.isNotEmpty()) {
                LazyRow(
                    modifier = modifier
                        .padding(
                            top = 8.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 8.dp
                        )
                        .align(Alignment.CenterHorizontally)
                ) {
                    items(items = memory.imageList) { photo ->
                        if (photo != null) {
                            IndiaryPhoto(photo = photo)
                        }
                    }
                }
            } else {
                Text(
                    text = "사진을 추가 해보세요",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MemoryCardPrev() {
    IndiaryTheme {
        Column {
            MemoryCard(onCardClick = {}, memory = DomainMemory(title = "놀러가기", date = "2018-11-11" ))
        }
    }
}
