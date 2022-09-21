package com.kguard.indiary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val useCase: PersonUseCase
):ViewModel(){
    private var _persons = MutableStateFlow<List<DomainPerson>>(emptyList())
    val persons: StateFlow<List<DomainPerson>>
    get() = _persons

    fun clearPerson(){
        viewModelScope.launch {
            _persons.value= emptyList()
        }
    }
    fun getPersons(){
        viewModelScope.launch {
            _persons.value=useCase.getPersons()
        }
    }
    fun deletePerson(person: DomainPerson)
    {
        viewModelScope.launch() {
            launch{useCase.deletePerson(person)}.join()
            getPersons()
        }
    }
    fun updatePerson(person: DomainPerson)
    {
        viewModelScope.launch {
            useCase.updatePerson(person)
            getPersons()
        }
    }
}