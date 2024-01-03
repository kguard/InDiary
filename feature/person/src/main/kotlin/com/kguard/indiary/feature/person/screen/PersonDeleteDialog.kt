package com.kguard.indiary.feature.person.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.designsystem.R

@Composable
fun PersonDeleteDialog(){

}

@Composable
fun PersonDeleteDialogContents(
    onClick : () -> Unit,
    onDismiss: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(modifier = Modifier.padding(32.dp),text = stringResource(id = R.string.DeletePerson), style = MaterialTheme.typography.labelLarge)
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onClick) {
                Text(text = stringResource(id = R.string.Cancel))
            }
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.Delete))
            }
        }
    }
}

@Preview
@Composable
fun PersonDeletePrev(){
    IndiaryTheme {
        PersonDeleteDialogContents()
    }
}