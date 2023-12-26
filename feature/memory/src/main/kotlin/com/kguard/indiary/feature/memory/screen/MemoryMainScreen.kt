package com.kguard.indiary.feature.memory.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.component.IndiaryMainTopAppBar
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.ui.MemoryCard
import com.kguard.indiary.feature.memory.viewmodel.MemoryViewModel

@Composable
internal fun MemoryMainRoute(
    viewModel: MemoryViewModel = viewModel(),
    onCardClick: (Int) -> Unit,
    onAddClick: () -> Unit,
) {
    viewModel.getMemories()
    val memories by viewModel.memory.collectAsStateWithLifecycle()
    MemoryMainScreen(
        memories = memories,
        onCardClick = onCardClick,
        onAddClick = onAddClick,
        onCardSlide = viewModel::deleteMemory,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MemoryMainScreen(
    onCardClick: (Int) -> Unit,
    onCardSlide: (DomainMemory) -> Unit,
    onAddClick: () -> Unit,
    memories: List<DomainMemory>,
) {
    Scaffold(
        topBar = {
            IndiaryMainTopAppBar(
                onNavigationClick = onAddClick,
                actionIcon = R.drawable.ic_memory_add,
                actionIconContentDescription = "AddMemory"
            )
        }
    )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(items = memories) { memory ->
                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        onCardSlide(memory)
                        true
                    }
                )
                SwipeToDismiss(
                    state = dismissState, background = { Color.Transparent }, dismissContent = {
                        MemoryCard(
                            memory = memory,
                            onCardClick = onCardClick,
                        )
                    })
            }
        }
    }
}

@Preview
@Composable
fun MemoryMainScreenPrev() {
    IndiaryTheme {
        MemoryMainScreen(
            onCardClick = {},
            onCardSlide = {},
            onAddClick = { /*TODO*/ },
            memories = listOf(
                DomainMemory(
                    title = "rlarudgh",
                    date = "2018-11-11",
                    imageList = arrayListOf("1", "2")
                )
            )
        )
    }
}