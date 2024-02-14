package com.kguard.indiary.core.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme

@Composable
fun QuitDialog(
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit
){
        AlertDialog(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.AskQuit),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            onDismissRequest = { onDismissRequest() },
            confirmButton = {
                TextButton(onClick = { onConfirmation() }) {
                    Text(text = stringResource(id = R.string.Quit))
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissRequest() }) {
                    Text(text = stringResource(id = R.string.Cancel))
                }
            }
        )
}
@Composable
@Preview
fun QuitDialogPrev()
{
    IndiaryTheme {
        QuitDialog(onConfirmation = { /*TODO*/ }, onDismissRequest = {})
    }
}