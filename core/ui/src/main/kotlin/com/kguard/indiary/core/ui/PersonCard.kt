package com.kguard.indiary.core.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.component.IndiaryToggleButton
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonCard(
    onClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    name: String,
    date: String,
    ){
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = Color.White
        ),
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(20))
            .border(3.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20))
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.End
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = name, style = MaterialTheme.typography.bodyLarge)
                IndiaryToggleButton(
                    checked = false,
                    onCheckedChange = onCheckedChange,
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star_line),
                            contentDescription = null
                        )
                    },
                    checkedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star_fill),
                            contentDescription = null
                        )
                    })

            }
            Text(text = date, style = MaterialTheme.typography.bodyMedium, modifier= Modifier.padding(end =16.dp, bottom = 16.dp))

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonCard2(
    onClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    name: String,
    date: String,
){
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = Color.White
        ),
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(20))
            .border(3.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20))
    ) {
        Row(
            modifier = modifier.padding(8.dp)
        ) {Text(text = name, style = MaterialTheme.typography.bodyLarge ,modifier= Modifier.weight(1f).padding(16.dp))
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(16.dp),
            ) {

                IndiaryToggleButton(
                    modifier =  Modifier.padding(bottom = 16.dp),
                    checked = false,
                    onCheckedChange = onCheckedChange,
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star_line),
                            contentDescription = null
                        )
                    },
                    checkedIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star_fill),
                            contentDescription = null
                        )
                    })
                Text(text = date, style = MaterialTheme.typography.bodyMedium)
            }


        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardPrev() {
    IndiaryTheme {
        Column {
            PersonCard(onClick = {}, name = "김경호", date = "2018-22-22", onCheckedChange = { !it
            })
            PersonCard2(onClick = {}, name = "김경호", date = "2018-22-22", onCheckedChange = { !it
            })
        }
    }
}