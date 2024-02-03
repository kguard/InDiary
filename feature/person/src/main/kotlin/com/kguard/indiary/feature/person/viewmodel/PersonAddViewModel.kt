package com.kguard.indiary.feature.person.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.indiary.core.domain.PersonUseCase
import com.kguard.indiary.core.model.DomainPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonAddViewModel @Inject constructor(
    private val personUseCase: PersonUseCase,
) : ViewModel() {
    fun insertPerson(person: DomainPerson) {
        viewModelScope.launch() {
            personUseCase.insertPerson(person)
        }
    }

}