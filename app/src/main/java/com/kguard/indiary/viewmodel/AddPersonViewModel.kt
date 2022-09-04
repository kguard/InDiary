package com.kguard.indiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPersonViewModel @Inject constructor(
    private val useCase: PersonUseCase
):ViewModel(){
    fun insertPerson(person:DomainPerson)
    {
        viewModelScope.launch() {
            useCase.insertPerson(person)
        }
    }
}