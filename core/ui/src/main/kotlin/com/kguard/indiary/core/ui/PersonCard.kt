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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.component.IndiaryToggleButton
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.model.DomainPerson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonCard(
    person : DomainPerson,
    onCardClick: (Int) -> Unit,
    onCheckedChange: (DomainPerson) -> Unit,
    check : Boolean,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = { onCardClick(person.personId) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = Color.White
        ),
        modifier = modifier
            .padding(8.dp)
            .border(3.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20)),
        shape = RoundedCornerShape(20),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Column(
            modifier = modifier.padding(6.dp),
            horizontalAlignment = Alignment.End,
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = person.name, style = MaterialTheme.typography.bodyLarge)
                IndiaryToggleButton(
                    checked = check,
                    onCheckedChange = { onCheckedChange(person.copy(favorite = !person.favorite)) },
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
            Text(
                text = person.make,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier.padding(end = 16.dp, bottom = 16.dp)
            )

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
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = Color.White
        ),
        modifier = modifier
            .padding(8.dp)
            .border(3.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(20)),
        shape = RoundedCornerShape(20),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Row(
            modifier = modifier.padding(6.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            )
            Column(
                horizontalAlignment = Alignment.End,
                modifier = modifier.padding(16.dp),
            ) {

                IndiaryToggleButton(
                    modifier = modifier.padding(bottom = 16.dp),
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

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CardPrev() {
//    IndiaryTheme {
//        Column {
//            PersonCard(person = , onCardClick = , onCheckedChange = , check = )
//        }
//    }
//}