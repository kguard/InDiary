package com.kguard.indiary.feature.person.screen

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kguard.indiary.core.designsystem.component.IndiaryMainTopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.core.ui.PersonCard
import com.kguard.indiary.feature.person.R
import com.kguard.indiary.feature.person.viewmodel.PersonMainViewModel

//Todo: lazyColumn, Dialog, SwipeToDismiss 관련 해결
@Composable
internal fun PersonMainRoute(
    personMainViewModel: PersonMainViewModel = hiltViewModel(),
    onCardClick: (Int) -> Unit,
) {
    personMainViewModel.getPersons()
    personMainViewModel.getMemories()
    val person by personMainViewModel.persons.collectAsStateWithLifecycle()
    val memory by personMainViewModel.memories.observeAsState()
    PersonMainScreen(
        onCardClick = onCardClick,
        onCardSlide = personMainViewModel::deletePerson,
        onCheckedChange = personMainViewModel::updatePerson,
        onRefresh = { personMainViewModel.getPersons() },
        persons = person,
        memories = memory
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun PersonMainScreen(
    onCardClick: (Int) -> Unit,
    onCheckedChange: (DomainPerson) -> Unit,
    onCardSlide: (DomainPerson) -> Unit,
    onRefresh: () -> Unit,
    persons: List<DomainPerson>? = null,
    memories: List<DomainMemory>? = null
) {
    val contextForToast = LocalContext.current.applicationContext
    var openDialog by remember { mutableStateOf(false) }
    var deletePerson by remember { mutableStateOf(DomainPerson()) }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
        {
            if (persons != null) {
                items(items = persons,
                    key = { person -> person.person_id }) { person ->
                    val dismissState = rememberDismissState(
                        positionalThreshold = { it * 0.5f },
                        confirmValueChange = {
                            if (it == DismissValue.DismissedToStart) {
                                openDialog = true
                                deletePerson = person
                                true
                            } else
                                false
                        }
                    )

//                    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
//                        ///onCardSlide(person)
//                        //dismiss 안됨
//                        //openDialog = true
//                        // Todo: Dialog 해야함
//                        if (openDialog) {
//                            PersonDeleteDialog(
//                                person = person,
//                                onConfirmation = {
//                                    openDialog = false
//                                    if (memories != null) {
//                                        if (memories.find { it.person_id == person.person_id } == null)
//                                            onCardSlide(person)
//                                        else
//                                            Toast.makeText(
//                                                contextForToast,
//                                                "삭제 할 수 없습니다.",
//                                                Toast.LENGTH_SHORT
//                                            ).show()
//                                    } else
//                                        onCardSlide(person)
//                                },
//                                onDismissRequest = {
//                                    onRefresh()
//                                    openDialog = false
//                                },
//                            )
//                        }
//                    }
//                if (openDialog) {
//                    PersonDeleteDialog(
//                        person = person,
//                        onConfirmation = {
//                            openDialog = false
//                            if (memories != null) {
//                                if (memories.find { it.person_id == person.person_id } == null)
//                                    onCardSlide(person)
//                                else
//                                    Toast.makeText(contextForToast, "삭제 할 수 없습니다.", Toast.LENGTH_SHORT).show()
//                            }
//                            else
//                                onCardSlide(person)
//                        },
//                        onDismissRequest = {
//                            openDialog = false
//                        },
//                    )
//                }
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
                        },
                        dismissContent = {
                            PersonCard(
                                person = person,
                                onCardClick = onCardClick,
                                check = person.favorite,
                                onCheckedChange = onCheckedChange,
                            )
                        }
                    )
                }
            }
        }
        if (openDialog) {
            PersonDeleteDialog(
                person = deletePerson,
                onConfirmation = {
                    openDialog = false
                    if (memories != null) {
                        if (memories.find { it.person_id == deletePerson.person_id } == null)
                            onCardSlide(deletePerson)
                        else
                            Toast.makeText(contextForToast, "삭제 할 수 없습니다.", Toast.LENGTH_SHORT)
                                .show()
                    } else
                        onCardSlide(deletePerson)
                },
                onDismissRequest = {
                    onRefresh()
                    openDialog = false
                },
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun PersonMainScreenPrev() {
    var alist by remember {
        mutableStateOf(
            listOf(
                DomainPerson(
                    person_id = 0,
                    name = "aaa",
                    favorite = true,
                    gender = 0,
                    make = "123",
                    birth = "123",
                    memo = "!231"
                ),
                DomainPerson(
                    person_id = 1,
                    name = "bbb",
                    favorite = true,
                    gender = 0,
                    make = "123",
                    birth = "123",
                    memo = "!231"
                ),
                DomainPerson(
                    person_id = 2,
                    name = "ccc",
                    favorite = true,
                    gender = 0,
                    make = "123",
                    birth = "123",
                    memo = "!231"
                )
            )
        )
    }


    IndiaryTheme {
        PersonMainScreen(
            onCardClick = {},
            persons = alist,
            onCardSlide = { alist = alist - it },
            onCheckedChange = {},
            onRefresh = {
                //alist = b
            }
        )
    }
}