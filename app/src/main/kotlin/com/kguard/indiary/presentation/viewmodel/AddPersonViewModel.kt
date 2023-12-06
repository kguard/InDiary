package com.kguard.indiary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.core.domain.CharacterUseCase
import com.kguard.core.domain.PersonUseCase
import com.kguard.core.model.DomainPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPersonViewModel @Inject constructor(
    private val PersonUseCase: PersonUseCase,
    private val CharacterUseCase: CharacterUseCase
) : ViewModel() {
    fun insertPerson(person: DomainPerson) {
        viewModelScope.launch() {
            PersonUseCase.insertPerson(person)
        }
    }

}