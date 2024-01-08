package com.kguard.indiary.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme

@Composable
fun IndiaryTextLine(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column {
        Row(modifier.padding(16.dp))
        {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                modifier = modifier.padding(horizontal = 8.dp),
                text = content,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Divider(
            modifier = modifier.padding(start = 16.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.primary,
            thickness = 2.dp
        )
    }
}
@Composable
fun IndiaryText(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column {
        Row(modifier.padding(16.dp))
        {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                modifier = modifier.padding(horizontal = 8.dp),
                text = content,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun IndiaryMultiTextLine(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column {
        Row(modifier.padding(16.dp))
        {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Column(
                modifier = modifier
                    .wrapContentHeight()
                    .size(160.dp)
                    .padding(horizontal = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(10.dp),
                    ),
            ) {
                Text(
                    modifier = modifier.padding(8.dp),
                    text = content,
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

        }
        Divider(
            modifier = modifier.padding(start = 16.dp, end = 16.dp),
            color = MaterialTheme.colorScheme.primary,
            thickness = 2.dp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun IndiaryTextLinePrev() {
    IndiaryTheme {
        Column {
            IndiaryTextLine(
                title = stringResource(id = R.string.NameTitle),
                content = "김경호",
                modifier = Modifier.fillMaxWidth()
            )
            IndiaryTextLine(
                title = stringResource(id = R.string.NameTitle),
                content = "김경호",
                modifier = Modifier.fillMaxWidth()
            )
            IndiaryTextLine(
                title = stringResource(id = R.string.NameTitle),
                content = "김경호",
                modifier = Modifier.fillMaxWidth()
            )
            IndiaryTextLine(
                title = stringResource(id = R.string.NameTitle),
                content = "김경호",
            )
            IndiaryText(
                title = stringResource(id = R.string.NameTitle),
                content = "김경호",
            )
            IndiaryMultiTextLine(
                title = stringResource(id = R.string.NameTitle),
                content = "",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}