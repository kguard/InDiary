package com.kguard.indiary.feature.person.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(showSystemUi = true)
@Composable
fun PersonDeletePrev() {
    IndiaryTheme {
        Column(modifier = Modifier.fillMaxSize()){
           PersonDeleteDialog(DomainPerson(
               personId = 0,
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