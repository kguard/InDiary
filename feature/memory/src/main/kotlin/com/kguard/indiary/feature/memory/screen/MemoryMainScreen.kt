package com.kguard.indiary.feature.memory.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.ui.MemoryCard
import com.kguard.indiary.feature.memory.viewmodel.MemoryMainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MemoryMainRoute(
    memoryMainViewModel: MemoryMainViewModel = hiltViewModel(),
    onCardClick: (Int) -> Unit,
) {
    memoryMainViewModel.getMemories()
    val memories by memoryMainViewModel.memory.collectAsStateWithLifecycle()
    MemoryMainScreen(
        memories = memories,
        onCardClick = onCardClick,
        onCardSlide = memoryMainViewModel::deleteMemory,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MemoryMainScreen(
    onCardClick: (Int) -> Unit,
    onCardSlide: (DomainMemory) -> Unit,
    memories: List<DomainMemory>,
) {
    var openDialog by remember { mutableStateOf(false) }
    var deleteMemory by remember { mutableStateOf(DomainMemory()) }
    Column(modifier = Modifier.fillMaxSize())
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(items = memories) { memory ->
                val dismissState = rememberDismissState(
                    positionalThreshold = { it * 0.5f },
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToStart) {
                            openDialog = true
                            deleteMemory = memory
                            true
                        } else
                            false
                    }
                )
                if (dismissState.currentValue != DismissValue.Default) {
                    if (!openDialog)
                        LaunchedEffect(Unit) {
                            dismissState.reset()
                        }
                }
                SwipeToDismiss(
                    modifier = Modifier.animateItemPlacement(),
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = Color.Transparent
                                ),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.padding(end = 8.dp),
                                imageVector = Icons.Default.Delete,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                contentDescription = null
                            )
                        }
                    }, dismissContent = {
                        MemoryCard(
                            memory = memory,
                            onCardClick = onCardClick,
                        )
                    })
            }
        }
        if (openDialog) {
            MemoryDeleteDialog(
                memory = deleteMemory,
                onConfirmation = {
                    openDialog = false
                    onCardSlide(deleteMemory)
                },
                onDismissRequest = {
                    openDialog = false
                }
            )
        }
    }
}

