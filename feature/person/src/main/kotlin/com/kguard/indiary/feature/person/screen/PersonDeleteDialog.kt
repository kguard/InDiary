package com.kguard.indiary.feature.person.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.model.DomainPerson

@Composable
fun PersonDeleteDialog(
    person: DomainPerson,
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        title = {
            Text(
                text = person.name,
                style = MaterialTheme.typography.displayLarge
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.DeletePerson),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(text = stringResource(id = R.string.Delete))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = stringResource(id = R.string.Cancel))
            }
        }
    )

}

@Preview
@Composable
fun PersonDeletePrev() {
    IndiaryTheme {
        Column{
           PersonDeleteDialog(DomainPerson(
               person_id = 0,
               name = "aaa",
               favorite = true,
               gender = 0,
               make = "123",
               birth = "123",
               memo = "!231"
           ),onConfirmation = {}, onDismissRequest = {})
        }
    }
}