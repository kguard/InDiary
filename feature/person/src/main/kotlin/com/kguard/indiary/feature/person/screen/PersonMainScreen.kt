package com.kguard.indiary.feature.person.screen

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kguard.indiary.core.designsystem.component.IndiaryMainTopAppBar
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.core.ui.PersonCard
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.feature.person.viewmodel.PersonViewModel

@Composable
internal fun PersonMainRoute(
    viewModel: PersonViewModel = viewModel(),
    onCardClick: (Int) -> Unit,
    onAddClick: () -> Unit,
) {
    viewModel.getPersons()
    val person by viewModel.persons.collectAsStateWithLifecycle()
    val memory by viewModel.memories.observeAsState()
    PersonMainScreen(
        onCardClick = onCardClick,
        onCardSlide = viewModel::deletePerson,
        onCheckedChange = viewModel::updatePerson,
        onAddClick = onAddClick,
        persons = person,
        memories = memory
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PersonMainScreen(
    onCardClick: (Int) -> Unit,
    onCheckedChange: (DomainPerson) -> Unit,
    onCardSlide: (DomainPerson) -> Unit,
    onAddClick: () -> Unit,
    persons: List<DomainPerson>,
    memories: List<DomainMemory>? = null
) {
    Scaffold(
        topBar = {
            IndiaryMainTopAppBar(
                onNavigationClick = onAddClick,
                actionIcon = R.drawable.ic_person_add,
                actionIconContentDescription = "AddPerson"
            )
        }
    )
    {
//        Column {
//            IndiaryMainTopAppBar(
//                onNavigationClick = onAddClick,
//                actionIcon = R.drawable.ic_person_add,
//                actionIconContentDescription = "AddPerson"
//            )
            LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp))
            {
                items(items = persons) { person ->
                    val dismissState = rememberDismissState(
                        confirmValueChange = {
                            if (memories != null) {
                                if (memories.find { it.person_id == person.person_id } == null)
                                    onCardSlide(person)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState, background = { Color.Transparent }, dismissContent = {
                            PersonCard(
                                person = person,
                                onCardClick = onCardClick,
                                onCheckedChange = onCheckedChange,
                            )
                        })
                }
            }
//        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PersonMainScreenPrev() {
    IndiaryTheme {
        PersonMainScreen(
            onCardClick = {},
            onAddClick = {},
            persons = listOf( DomainPerson(person_id = 0, name = "aaa", favorite = true, gender = 0, make = "123", birth = "123", memo = "!231")),
            onCardSlide = {},
            onCheckedChange = {})
    }
}