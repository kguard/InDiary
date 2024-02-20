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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.core.ui.PersonCard
import com.kguard.indiary.feature.person.viewmodel.PersonMainViewModel


@Composable
internal fun PersonMainRoute(
    personMainViewModel: PersonMainViewModel = hiltViewModel(),
    onCardClick: (Int) -> Unit,
) {
    personMainViewModel.getPersons()
    personMainViewModel.getMemories()
    val person by personMainViewModel.persons.collectAsStateWithLifecycle()
    val memory by personMainViewModel.memories.collectAsStateWithLifecycle()
    PersonMainScreen(
        onCardClick = onCardClick,
        onCardSlide = personMainViewModel::deletePerson,
        onCheckedChange = personMainViewModel::updatePerson,
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
                    key = { person -> person.personId }) { person ->
                    val dismissState = rememberSwipeToDismissBoxState(
                        positionalThreshold = { it * 0.5f },
                        confirmValueChange = {
                            if (it == SwipeToDismissBoxValue.EndToStart) {
                                openDialog = true
                                deletePerson = person
                            }
                            true
                        }
                    )
                    if (dismissState.currentValue != SwipeToDismissBoxValue.Settled) {
                        if (!openDialog)
                            LaunchedEffect(Unit) {
                                dismissState.reset()
                            }
                    }
                    SwipeToDismissBox(
                        modifier = Modifier.animateItemPlacement(),
                        state = dismissState,
                        enableDismissFromStartToEnd = false,
                        backgroundContent = {
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
                        content = {
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
    }
    if (openDialog) {
        PersonDeleteDialog(
            person = deletePerson,
            onConfirmation = {
                if (memories != null) {
                    if (memories.find { it.personId == deletePerson.personId } == null)
                        onCardSlide(deletePerson)
                    else
                        Toast.makeText(contextForToast, "삭제 할 수 없습니다.", Toast.LENGTH_SHORT)
                            .show()
                } else
                    onCardSlide(deletePerson)
                openDialog = false
            },
            onDismissRequest = {
                openDialog = false
            },
        )
    }
}
