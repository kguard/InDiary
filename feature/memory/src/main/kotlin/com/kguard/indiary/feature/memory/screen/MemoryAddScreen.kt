package com.kguard.indiary.feature.memory.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.component.IndiaryMultilineTextField
import com.kguard.indiary.core.designsystem.component.IndiaryPhoto
import com.kguard.indiary.core.designsystem.component.IndiarySubTopAppBar
import com.kguard.indiary.core.designsystem.component.IndiaryTextButton
import com.kguard.indiary.core.designsystem.component.IndiaryTextField
import com.kguard.indiary.core.designsystem.util.addFocusCleaner
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.feature.memory.component.MemoryDateRangePicker
import com.kguard.indiary.feature.memory.component.MemoryPhotoPicker
import com.kguard.indiary.feature.memory.component.MemoryWithDialog
import com.kguard.indiary.feature.memory.viewmodel.MemoryAddViewModel


@Composable
fun MemoryAddRoute(
    memoryAddViewModel: MemoryAddViewModel = hiltViewModel(),
    onCompleteClick: () -> Unit,
) {
    val persons by memoryAddViewModel.persons.collectAsStateWithLifecycle()
    MemoryAddScreen(
        persons = persons,
        onAddClick = {
            memoryAddViewModel.insertMemory(it)
            onCompleteClick()
        },
        onBackClick = onCompleteClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MutableCollectionMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun MemoryAddScreen(
    modifier: Modifier = Modifier,
    persons: List<DomainPerson>,
    onAddClick: (DomainMemory) -> Unit,
    onBackClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    var title by remember { mutableStateOf("") }
    var isEmptyTitle by remember { mutableStateOf(false) }

    var with by remember { mutableStateOf<DomainPerson?>(null) }

    var date by remember { mutableStateOf("") }
    var isEmptyDate by remember { mutableStateOf(false) }

    var content by remember { mutableStateOf("") }

    var photos by rememberSaveable { mutableStateOf(listOf<String?>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager = focusManager)
    ) {
        IndiarySubTopAppBar(
            title = stringResource(R.string.AddMemoryPage),
            navigationIcon = R.drawable.ic_back,
            onNavigationClick = onBackClick
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IndiaryTextField(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),
                value = title,
                onValueChange = { newValue -> title = newValue },
                labelId = R.string.Title,
                isError = isEmptyTitle,
                supportingText = { if (isEmptyTitle) Text(text = stringResource(id = R.string.TitleEmptyError)) }
            )
            Row(
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        bottom = 4.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MemoryWithDialog(
                    persons = persons,
                    onItemClick = { with = it })
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = with?.name ?: "함께한 사람을 선택해주세요.",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                        bottom = 4.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MemoryDateRangePicker(onConfirm = { date = it })
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = if (date == "")
                        "기간을 선택해주세요."
                    else
                        date,
                    color = if (isEmptyDate) Color.Red else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Row(
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        bottom = 4.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (photos.size < 5) {
                    MemoryPhotoPicker(onPhotoSelected = {
                        photos = photos + it
                    })
                }
                if (photos.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    )
                    {
                        items(items = photos) { photo ->
                            if (photo != null) {
                                IndiaryPhoto(
                                    photo = photo,
                                    modifier = Modifier
                                        .clickable {
                                            photos = photos - photo
                                        },
                                )
                            }
                        }
                    }
                } else {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                        text = "사진을 추가해보세요.",
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            IndiaryMultilineTextField(
                value = content,
                onValueChange = { newValue -> content = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                labelId = R.string.Content,
            )
        }
        IndiaryTextButton(
            onClick = {
                isEmptyDate = date == ""
                isEmptyTitle = title == ""
                if (!isEmptyTitle && !isEmptyDate) {
                        onAddClick(
                            DomainMemory(
                                title = title,
                                date = date,
                                content = content,
                                imageList = photos,
                                personId = with?.personId
                            )
                        )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = stringResource(id = R.string.Complete),
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}

