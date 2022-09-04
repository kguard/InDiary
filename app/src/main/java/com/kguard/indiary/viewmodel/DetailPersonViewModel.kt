package com.kguard.indiary.viewmodel

import androidx.lifecycle.ViewModel
import com.kguard.indiary.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPersonViewModel @Inject constructor(
    private val useCase: PersonUseCase
):ViewModel() {
    //fun getPerson()
}