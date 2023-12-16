package com.kguard.indiary.feature.person.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kguard.indiary.feature.person.viewmodel.PersonViewModel

@Composable
internal fun PersonMainScreen(
    viewModel: PersonViewModel = viewModel(),
    onCardClick: (Int) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
)
{

}
@Preview
@Composable
fun PersonMainScreenPrev(){

}