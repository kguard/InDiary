package com.kguard.indiary.presentation.viewmodel

import androidx.lifecycle.*
import com.kguard.core.domain.MemoryUseCase
import com.kguard.core.domain.PersonUseCase
import com.kguard.core.model.DomainMemory
import com.kguard.core.model.DomainPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val personUseCase: PersonUseCase,
    private val memoryUseCase: MemoryUseCase
) : ViewModel() {
    private var _persons = MutableStateFlow<List<DomainPerson>>(emptyList())
    val persons: StateFlow<List<DomainPerson>>
        get() = _persons

    private val _memories = MutableLiveData<List<DomainMemory>>()
    val memories: LiveData<List<DomainMemory>>
        get() = _memories

    fun getMemoriesInPerson() {
        viewModelScope.launch {
            _memories.value = memoryUseCase.getMemories()
        }
    }

    fun clearPerson() {
        viewModelScope.launch {
            _persons.value = emptyList()
        }
    }

    fun getPersons() {
        viewModelScope.launch {
            _persons.value = personUseCase.getPersons()
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