package com.kguard.indiary.feature.memory.screen

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
fun MemoryDeleteDialog(
    memoryName: String,
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        title = {
            Text(
                text = memoryName,
                style = MaterialTheme.typography.displayLarge
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.DeleteMemory),
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
fun MemoryDeleteDialogPrv() {
    IndiaryTheme {
        MemoryDeleteDialog(memoryName = "집에 가기", onConfirmation = {}, onDismissRequest = {})
    }
}
