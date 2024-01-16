package com.kguard.indiary.feature.memory.screen

import android.annotation.SuppressLint
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.component.IndiaryFloatingActionButton
import com.kguard.indiary.core.designsystem.component.IndiaryMultiTextLine
import com.kguard.indiary.core.designsystem.component.IndiaryTextLine
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.core.ui.MemoryPhoto
import com.kguard.indiary.feature.memory.viewmodel.DetailMemory2ViewModel


@Composable
fun MemoryDetailRoute(
    memoryDetailViewModel: DetailMemory2ViewModel = viewModel(),
    onUpdateClick: (DomainMemory) -> Unit,
    onDeleteClick: () -> Unit,
    memoryId: Int
) {
    memoryDetailViewModel.getMemory(memoryId)
    val memory by memoryDetailViewModel.memory.observeAsState(initial = DomainMemory())
    memory.person_id?.let { memoryDetailViewModel.getPerson(it) }
    val person by memoryDetailViewModel.person.observeAsState()
    MemoryDetailScreen(
        memory = memory,
        person = person,
        onUpdateClick = onUpdateClick,
        onDeleteClick = {
            memoryDetailViewModel.deleteMemory(it)
            onDeleteClick()
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MemoryDetailScreen(
    modifier: Modifier = Modifier,
    memory: DomainMemory,
    person: DomainPerson? = null,
    onUpdateClick: (DomainMemory) -> Unit,
    onDeleteClick: (DomainMemory) -> Unit,
) {
    val contextForToast = LocalContext.current.applicationContext
    var openDialog by remember { mutableStateOf(false) }
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
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
                                    MemoryPhoto(photo = photo)
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
                    onClick = { onUpdateClick(memory) },
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
}

@Preview(showSystemUi = true)
@Composable
fun MemoryDetailScreenPrev() {
    IndiaryTheme {
        MemoryDetailScreen(memory = DomainMemory(
            title = "rlarudgh",
            date = "2018-11-11",
            person_id = 0,
            imageList = arrayListOf("1", "2")
        ), person = DomainPerson(
            person_id = 0,
            name = "aaa",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "19991101",
            memo = "fafasdfasdfadsfasdfasdfadsfasdfasasdfadfaadfadsfasdfasdfasdfasdfasdfdfasdfasdfadsfadafsdfasdfaasdfasdfadfasdfasdfasdasdfa2sadasdassdfjasdklfjasdjkfasdjfa;klsdjfak;lsjdf;kajsdkl;fja;lskdfj;asdjkfa;ksd31"
        ), onUpdateClick = {}, onDeleteClick = {}
        )
    }
}