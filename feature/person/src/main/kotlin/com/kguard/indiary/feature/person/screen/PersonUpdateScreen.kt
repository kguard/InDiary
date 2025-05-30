package com.kguard.indiary.feature.person.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.component.IndiaryMultilineTextField
import com.kguard.indiary.core.designsystem.component.IndiaryNumField
import com.kguard.indiary.core.designsystem.component.IndiarySubTopAppBar
import com.kguard.indiary.core.designsystem.component.IndiaryTextButton
import com.kguard.indiary.core.designsystem.component.IndiaryTextField
import com.kguard.indiary.core.designsystem.util.addFocusCleaner
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.feature.person.viewmodel.PersonDetailViewModel
import java.util.regex.Pattern

@Composable
fun PersonUpdateRoute(
    personDetailViewModel: PersonDetailViewModel,
    onCompleteClick: () -> Unit,
) {
    val persons by personDetailViewModel.person.collectAsStateWithLifecycle()
    PersonUpdateScreen(
        onUpdateClick = {
            personDetailViewModel.updatePerson(it)
            onCompleteClick()
        },
        person = persons,
        onBackClick = onCompleteClick
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonUpdateScreen(
    modifier: Modifier = Modifier,
    person: DomainPerson,
    onUpdateClick: (DomainPerson) -> Unit,
    onBackClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    var name by remember { mutableStateOf(person.name) }
    var isEmptyName by remember { mutableStateOf(false) }

    var birth by remember { mutableStateOf(person.birth ?: "") }
    var isEmptyBirth by remember { mutableStateOf(false) }

    var gender by remember { mutableIntStateOf(person.gender) }
    var isExpanded by remember { mutableStateOf(false) }
    val menu = stringArrayResource(id = R.array.GenderDetail)

    var memo by remember { mutableStateOf(person.memo ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager = focusManager),
    )
    {
        IndiarySubTopAppBar(
            title = stringResource(R.string.UpdatePersonPage),
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
                value = name,
                onValueChange = { newValue -> name = newValue },
                labelId = R.string.Name,
                isError = isEmptyName,
                supportingText = { if (isEmptyName) Text(text = stringResource(id = R.string.NameEmptyError)) }
            )
            IndiaryNumField(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                value = birth,
                onValueChange = { newValue ->
                    birth = newValue
                },
                labelId = R.string.Age,
                isError = isEmptyBirth,
                supportingText = {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text(text = stringResource(id = if (isEmptyBirth) R.string.BirthError else R.string.BirthHint))
                        Text(text = "${birth.length}/8")
                    }
                }
            )
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                expanded = isExpanded,
                onExpandedChange = { isExpanded = it }
            ) {
                IndiaryTextField(
                    value = if (gender == -1) "" else menu[gender],
                    onValueChange = {},
                    readOnly = true,
                    labelId = R.string.Gender,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                    modifier = modifier
                        .fillMaxWidth()
                        .menuAnchor(type = MenuAnchorType.PrimaryNotEditable)
                        .onFocusChanged { isExpanded = it.isFocused }
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = {
                        isExpanded = false
                        focusManager.clearFocus()
                    },
                ) {
                    menu.forEachIndexed { index, string ->
                        DropdownMenuItem(
                            text = { Text(text = string) },
                            onClick = {
                                gender = index
                                isExpanded = false
                                focusManager.clearFocus()
                            })
                    }
                }
            }
            IndiaryMultilineTextField(
                value = memo,
                onValueChange = { newValue -> memo = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                labelId = R.string.Memo,
            )

        }
        IndiaryTextButton(
            onClick = {
                val pattern = Pattern.matches(
                    "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$",
                    birth
                )
                isEmptyName = name == ""
                isEmptyBirth = birth == "" || !pattern
                if (!isEmptyName && !isEmptyBirth) {
                    onUpdateClick(
                        person.copy(
                            name = name,
                            birth = birth,
                            gender = gender,
                            memo = memo
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
