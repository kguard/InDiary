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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.kguard.indiary.core.designsystem.component.IndiaryTextLine
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.component.IndiaryFloatingActionButton
import com.kguard.indiary.core.designsystem.component.IndiaryMultiTextLine
import com.kguard.indiary.core.designsystem.component.IndiaryTab
import com.kguard.indiary.core.designsystem.component.IndiaryTabRow
import com.kguard.indiary.core.designsystem.component.IndiaryText
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.feature.memory.screen.MemoryMainScreen
import com.kguard.indiary.feature.person.viewmodel.DetailPersonViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch


@Composable
fun PersonDetailRoute(
    personDetailViewModel: DetailPersonViewModel = viewModel(),
    onUpdateClick: (DomainPerson) -> Unit,
    onCardClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
    personId: Int
) {
    personDetailViewModel.getPerson(personId)
    personDetailViewModel.getMemoriesInPerson(personId)
    val person by personDetailViewModel.person.observeAsState(initial = DomainPerson())
    val memories by personDetailViewModel.memories.observeAsState(initial = listOf(DomainMemory()))
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
        }
    )

}

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun PersonDetailScreen(
    modifier: Modifier = Modifier,
    person: DomainPerson,
    age: String,
    memories: List<DomainMemory>?,
    onCardClick: (Int) -> Unit,
    onCardSlide: (DomainMemory) -> Unit,
    onUpdateClick: (DomainPerson) -> Unit,
    onDeleteClick: (DomainPerson) -> Unit,

    ) {
    val title = listOf("특징", "추억")
    val pageState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
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
}

@Composable
fun PersonFeatureScreen(
    modifier: Modifier = Modifier,
    person: DomainPerson,
    memories: List<DomainMemory>? = null,
    age: String,
    onUpdateClick: (DomainPerson) -> Unit,
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
                onClick = { onUpdateClick(person) },
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
    }
    if (openDialog) {
        PersonDeleteDialog(
            person = person,
            onConfirmation = {
                openDialog = false
                if (memories != null) {
                    if (memories.find { it.person_id == person.person_id } == null)
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

@Preview(showSystemUi = true)
@Composable
fun PersonFeaturePrev() {
    IndiaryTheme {
        PersonFeatureScreen(
            person = DomainPerson(
                person_id = 0,
                name = "aaa",
                favorite = true,
                gender = 0,
                make = "123",
                birth = "19991101",
//                memo = "fafasdfasdfadsfasdfasdfadsfasdfasasdfadfaadfadsfasdfasdfasdfasdfasdfdfasdfasdfadsfadafsdfasdfaasdfasdfadfasdfasdfasdasdfa2sadasdassdfjasdklfjasdjkfasdjfa;klsdjfak;lsjdf;kajsdkl;fja;lskdfj;asdjkfa;ksd31"
            ),
            age = "24",
            onUpdateClick = {},
            onDeleteClick = {})
    }
}

@Preview(showSystemUi = true)
@Composable
fun PersonDetailPrev() {
    IndiaryTheme {
        PersonDetailScreen(
            person = DomainPerson(
                person_id = 0,
                name = "aaa",
                favorite = true,
                gender = 0,
                make = "123",
                birth = "19991101",
                memo = "fafasdfasdfadsfasdfasdfadsfasdfasasdfadfaadfadsfasdfasdfasdfasdfasdfdfasdfasdfadsfadafsdfasdfaasdfasdfadfasdfasdfasdasdfa2sadasdassdfjasdklfjasdjkfasdjfa;klsdjfak;lsjdf;kajsdkl;fja;lskdfj;asdjkfa;ksd31"
            ),
            age = "24",
            onUpdateClick = {},
            onDeleteClick = {},
            onCardClick = {},
            onCardSlide = {},
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