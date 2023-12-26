package com.kguard.indiary.feature.person.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kguard.indiary.core.designsystem.component.IndiaryTextLine
import com.kguard.indiary.core.designsystem.theme.IndiaryTheme
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.core.designsystem.R
import com.kguard.indiary.core.designsystem.component.IndiaryFloatingActionButton
import com.kguard.indiary.core.designsystem.component.IndiaryMultiTextLine
import com.kguard.indiary.core.designsystem.component.IndiaryText


@Composable
fun PersonDetailRoute() {

}

@Composable
fun PersonDetailScreen() {

}

@Composable
fun PersonFeatureScreen(
    person: DomainPerson,
    age: String,
    modifier: Modifier = Modifier,
    onUpdateClick: (DomainPerson) -> Unit,
    onDeleteClick: (DomainPerson) -> Unit,
) {
    Column(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .weight(1f)
        )
        {
            IndiaryTextLine(title = stringResource(id = R.string.NameTitle), content = person.name)
            Row {
                IndiaryText(
                    title = stringResource(id = R.string.BirthTitle),
                    content = person.birth.toString()
                )
                IndiaryText(title = stringResource(id = R.string.AgeTitle), content = age)
            }
            Divider(
                modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                thickness = 2.dp
            )
            IndiaryTextLine(
                title = stringResource(id = R.string.GenderTitle), content =
                when (person.gender) {
                    0 -> stringResource(id = R.string.Male)
                    1 -> stringResource(id = R.string.Female)
                    else -> stringResource(id = R.string.NoneGender)
                }
            )
            person.memo?.let {
                IndiaryMultiTextLine(
                    modifier = modifier.fillMaxWidth(),
                    title = stringResource(R.string.MemoTitle),
                    content = it
                )
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.Right
        ) {
            IndiaryFloatingActionButton(
                modifier = modifier.padding(16.dp),
                onClick = { onUpdateClick(person) },
                icon = Icons.Rounded.Edit
            )
            IndiaryFloatingActionButton(
                modifier = modifier.padding(16.dp),
                onClick = { onDeleteClick(person) },
                icon = Icons.Rounded.Delete
            )
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun PersonFeaturePrev() {
    IndiaryTheme {
        PersonFeatureScreen(
            person = DomainPerson(
                person_id = 0,
                name = "aaa",
                favorite = true,
                gender = 0,
                make = "123",
                birth = "19991101",
                memo = "fafasdfasdfadsfasdfasdfadsfasdfasasdfadfaadfadsfasdfasdfasdfasdfasdfdfasdfasdfadsfadafsdfasdfaasdfasdfadfasdfasdfasdasdfa2sadasdassdfjasdklfjasdjkfasdjfa;klsdjfak;lsjdf;kajsdkl;fja;lskdfj;asdjkfa;ksd31"
            ),
            age = "24",
            onUpdateClick = {},
            onDeleteClick = {})
    }
}