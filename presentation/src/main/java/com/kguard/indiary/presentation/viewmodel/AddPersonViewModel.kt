package com.kguard.indiary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.model.DomainPerson
import com.kguard.domain.usecase.CharacterUseCase
import com.kguard.domain.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPersonViewModel @Inject constructor(
    private val PersonUseCase: PersonUseCase,
    private val CharacterUseCase: CharacterUseCase
):ViewModel(){
    fun insertPerson(person:DomainPerson)
    {
        viewModelScope.launch() {
            PersonUseCase.insertPerson(person)
        }
    }

}