package com.kguard.indiary.feature.person.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kguard.indiary.core.designsystem.component.IndiaryTextLine
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.component.IndiaryFloatingActionButton
import com.kguard.indiary.core.designsystem.component.IndiaryMultiTextLine
import com.kguard.indiary.core.designsystem.component.IndiaryTab
import com.kguard.indiary.core.designsystem.component.IndiaryTabRow
import com.kguard.indiary.core.designsystem.component.IndiaryText
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.feature.memory.screen.MemoryMainScreen
import com.kguard.indiary.feature.person.viewmodel.PersonDetailViewModel
import com.kguard.indiary.core.designsystem.component.IndiarySubTopAppBar
import kotlinx.coroutines.launch


@Composable
fun PersonDetailRoute(
    personDetailViewModel: PersonDetailViewModel = hiltViewModel(),
    onUpdateClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
    onBackClick: () -> Unit,
    personId: Int
) {
//    personDetailViewModel.getPerson(personId)
    val person by personDetailViewModel.person.collectAsStateWithLifecycle()
    val memories by personDetailViewModel.memories.collectAsStateWithLifecycle()
    PersonDetailScreen(
        person = person,
        age = personDetailViewModel.getAge(person.birth.toString()),
        memories = memories,
        onCardClick = onCardClick,
        onCardSlide = personDetailViewModel::deleteMemory,
        onUpdateClick = onUpdateClick,
        onDeleteClick = {
            personDetailViewModel.deletePerson(it)
            onDeleteClick()
        },
        onBackClick = onBackClick
    )

}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun PersonDetailScreen(
    modifier: Modifier = Modifier,
    person: DomainPerson,
    age: String,
    memories: List<DomainMemory>?,
    onCardClick: (Int) -> Unit,
    onCardSlide: (DomainMemory) -> Unit,
    onUpdateClick: () -> Unit,
    onDeleteClick: (DomainPerson) -> Unit,
    onBackClick: () -> Unit
) {
    val title = listOf("특징", "추억")
    val pageState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {
        IndiarySubTopAppBar(
            title = person.name,
            navigationIcon = R.drawable.ic_back,
            onNavigationClick = onBackClick
        )
        IndiaryTabRow(
            selectedTabIndex = pageState.currentPage,
        ) {
            title.forEachIndexed { index, title ->
                IndiaryTab(
                    selected = pageState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pageState.scrollToPage(index)
                        }
                    },
                    text = { Text(text = title) },
                )
            }

        }
        HorizontalPager(count = title.size, state = pageState) { page ->
            when (page) {
                0 -> PersonFeatureScreen(
                    person = person,
                    memories = memories,
                    age = age,
                    onUpdateClick = onUpdateClick,
                    onDeleteClick = onDeleteClick,
                )

                1 -> memories?.let { it1 ->
                    MemoryMainScreen(
                        onCardClick = onCardClick,
                        onCardSlide = onCardSlide,
                        memories = it1
                    )
                }
            }

        }
    }
}


@Composable
fun PersonFeatureScreen(
    modifier: Modifier = Modifier,
    person: DomainPerson,
    memories: List<DomainMemory>? = null,
    age: String,
    onUpdateClick: () -> Unit,
    onDeleteClick: (DomainPerson) -> Unit,
) {
    val contextForToast = LocalContext.current.applicationContext
    var openDialog by remember { mutableStateOf(false) }
    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .weight(1f)
        )
        {
            IndiaryTextLine(title = stringResource(id = R.string.NameTitle), content = person.name)
            Row {
                IndiaryText(
                    title = stringResource(id = R.string.BirthTitle),
                    content = person.birth.toString()
                )
                IndiaryText(title = stringResource(id = R.string.AgeTitle), content = age)
            }
            Divider(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                color = MaterialTheme.colorScheme.primary,
                thickness = 2.dp
            )
            IndiaryTextLine(
                title = stringResource(id = R.string.GenderTitle), content =
                when (person.gender) {
                    0 -> stringResource(id = R.string.Male)
                    1 -> stringResource(id = R.string.Female)
                    else -> stringResource(id = R.string.NoneGender)
                }
            )
            IndiaryMultiTextLine(
                modifier = modifier.fillMaxWidth(),
                title = stringResource(R.string.MemoTitle),
                content = person.memo ?: ""
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
                },
                icon = Icons.Rounded.Delete
            )
        }
    }
    if (openDialog) {
        PersonDeleteDialog(
            person = person,
            onConfirmation = {
                openDialog = false
                if (memories != null) {
                    if (memories.find { it.personId == person.personId } == null)
                        onDeleteClick(person)
                    else
                        Toast.makeText(contextForToast, "삭제 할 수 없습니다.", Toast.LENGTH_SHORT)
                            .show()
                } else
                    onDeleteClick(person)
            },
            onDismissRequest = {
                openDialog = false
            },
        )
    }
}