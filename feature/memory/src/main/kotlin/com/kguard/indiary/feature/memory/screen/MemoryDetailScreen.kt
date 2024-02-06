package com.kguard.indiary.feature.memory.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.component.IndiaryFloatingActionButton
import com.kguard.indiary.core.designsystem.component.IndiaryMultiTextLine
import com.kguard.indiary.core.designsystem.component.IndiaryPhoto
import com.kguard.indiary.core.designsystem.component.IndiarySubTopAppBar
import com.kguard.indiary.core.designsystem.component.IndiaryTextLine
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.feature.memory.viewmodel.MemoryDetailViewModel


@Composable
fun MemoryDetailRoute(
    memoryDetailViewModel: MemoryDetailViewModel = hiltViewModel(),
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onBackClick: () -> Unit,
    memoryId : Int
) {
    val memory by memoryDetailViewModel.memory.collectAsStateWithLifecycle()
    if(memory.personId != null) memoryDetailViewModel.getPerson(memory.personId!!) else memoryDetailViewModel.clearPerson()
    val memoryPerson by memoryDetailViewModel.person.collectAsStateWithLifecycle()
    MemoryDetailScreen(
        memory = memory,
        person = memoryPerson,
        onUpdateClick = onUpdateClick,
        onDeleteClick = {
            memoryDetailViewModel.deleteMemory(it)
            onDeleteClick()
        },
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MemoryDetailScreen(
    modifier: Modifier = Modifier,
    memory: DomainMemory,
    person: DomainPerson? = null,
    onUpdateClick: () -> Unit,
    onDeleteClick: (DomainMemory) -> Unit,
    onBackClick: () -> Unit
) {
    val contextForToast = LocalContext.current.applicationContext
    var openDialog by remember { mutableStateOf(false) }
    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        IndiarySubTopAppBar(
            title = memory.title,
            navigationIcon = R.drawable.ic_back,
            onNavigationClick = onBackClick
        )
        Column(
            modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            IndiaryTextLine(
                title = stringResource(id = R.string.TitleTitle),
                content = memory.title
            )
            IndiaryTextLine(
                title = stringResource(id = R.string.WithTitle), content = person?.name ?: ""
            )
            IndiaryTextLine(
                title = stringResource(id = R.string.DateTitle),
                content = memory.date
            )
            Row(modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.PhotoTitle),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (memory.imageList.isNotEmpty()) {
                    LazyRow(
                        modifier = modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(items = memory.imageList) { photo ->
                            if (photo != null) {
                                IndiaryPhoto(photo = photo)
                            }
                        }
                    }
                }
            }
            Divider(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp
            )
            IndiaryMultiTextLine(
                title = stringResource(id = R.string.ContentTitle),
                content = memory.content ?: "",
                modifier = modifier.fillMaxWidth()
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.Right
        ) {
            IndiaryFloatingActionButton(
                modifier = modifier.padding(16.dp),
                onClick = { onUpdateClick() },
                icon = Icons.Rounded.Edit
            )
            IndiaryFloatingActionButton(
                modifier = modifier.padding(16.dp),
                onClick = {
                    openDialog = true
                    // onDeleteClick(person)
                },
                icon = Icons.Rounded.Delete
            )
        }
        if (openDialog) {
            MemoryDeleteDialog(
                memory = memory,
                onConfirmation = {
                    openDialog = false
                    onDeleteClick(memory)
                    Toast.makeText(contextForToast, "삭제 되었습니다.", Toast.LENGTH_SHORT).show()
                },
                onDismissRequest = {
                    openDialog = false
                },
            )
        }
    }
}
