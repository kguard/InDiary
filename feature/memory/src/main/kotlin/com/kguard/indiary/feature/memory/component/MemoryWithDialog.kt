package com.kguard.indiary.feature.memory.component


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.model.DomainPerson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryWithDialog(
    persons: List<DomainPerson>,
    onItemClick: (DomainPerson?) -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }
    OutlinedButton(
        onClick = { openDialog = true },
        border = BorderStroke(
            1.dp, MaterialTheme.colorScheme.onPrimaryContainer
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colorScheme.background
        ),
        contentPadding = PaddingValues()
    )
    {
        Icon(
            modifier = Modifier.padding(start = 6.dp),
            imageVector = Icons.Rounded.Add,
            contentDescription = "add",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = stringResource(id = R.string.With),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.labelLarge
        )

    }
    if (openDialog) {
        Dialog(onDismissRequest = { openDialog = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 175.dp, maxHeight = 375.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 8.dp),
                    text = stringResource(id = R.string.With),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.displayLarge
                )
                if (persons.isNotEmpty()) {
                    LazyColumn {
                        items(items = persons) { person ->
                            Card(
                                modifier = Modifier.fillMaxSize(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.background
                                ),
                                onClick = {
                                    openDialog = false
                                    onItemClick(person)
                                },
                            )
                            {
                                Text(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    text = AnnotatedString(person.name),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    style = MaterialTheme.typography.displaySmall
                                )
                            }
                        }
                    }
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                        onClick = {
                            openDialog = false
                            onItemClick(null)
                        },
                    )
                    {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "선택 안함",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                } else {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally),
                        text = "With로 추가 할 사람이 없습니다.",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showSystemUi = true)
@Composable
private fun MemoryWithDialogButtonPrev() {
    val alist = mutableStateListOf(
        DomainPerson(
            personId = 0,
            name = "aaa",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 1,
            name = "bbb",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 2,
            name = "ccc",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 0,
            name = "aaa",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 1,
            name = "bbb",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 2,
            name = "ccc",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 0,
            name = "aaa",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 1,
            name = "bbb",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 2,
            name = "ccc",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ), DomainPerson(
            personId = 0,
            name = "aaa",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 1,
            name = "bbb",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 2,
            name = "ccc",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ), DomainPerson(
            personId = 0,
            name = "aaa",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 1,
            name = "bbb",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 2,
            name = "ccc",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ), DomainPerson(
            personId = 0,
            name = "aaa",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 1,
            name = "bbb",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 2,
            name = "ccc",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ), DomainPerson(
            personId = 0,
            name = "aaa",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 1,
            name = "bbb",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        ),
        DomainPerson(
            personId = 2,
            name = "ccc",
            favorite = true,
            gender = 0,
            make = "123",
            birth = "123",
            memo = "!231"
        )
    )
    IndiaryTheme {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            MemoryWithDialog(
                persons = alist,
                onItemClick = {}
            )
        }

    }
}