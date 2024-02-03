package com.kguard.indiary.feature.person.viewmodel

import androidx.lifecycle.*
import com.kguard.indiary.core.domain.MemoryUseCase
import com.kguard.indiary.core.domain.PersonUseCase
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.model.DomainPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonMainViewModel @Inject constructor(
    private val personUseCase: PersonUseCase,
    private val memoryUseCase: MemoryUseCase
) : ViewModel() {
    private var _persons = MutableStateFlow<List<DomainPerson>>(emptyList())
    val persons: StateFlow<List<DomainPerson>>
        get() = _persons

    private val _memories = MutableStateFlow<List<DomainMemory>>(emptyList())
    val memories: StateFlow<List<DomainMemory>>
        get() = _memories

    fun getPersons() {
        viewModelScope.launch {
            _persons.value = personUseCase.getPersons()
        }
    }
    fun getMemories() {
        viewModelScope.launch {
            _memories.value = memoryUseCase.getMemories()
        }
    }

    fun deletePerson(person: DomainPerson) {
        viewModelScope.launch() {
            launch { personUseCase.deletePerson(person) }.join()
            getPersons()
        }
    }

    fun updatePerson(person: DomainPerson) {
        viewModelScope.launch {
            personUseCase.updatePerson(person)
            getPersons()
        }
    }
}