package com.kguard.indiary.feature.memory.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class MemoryDetailViewModel @Inject constructor(
    private val memoryUseCase: MemoryUseCase,
    private val personUseCase: PersonUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _memory = MutableStateFlow<DomainMemory>(DomainMemory())
    val memory: StateFlow<DomainMemory>
        get() = _memory

    private var _person = MutableStateFlow<DomainPerson?>(null)
    val person: StateFlow<DomainPerson?>
        get() = _person

    private val _persons = MutableStateFlow<List<DomainPerson>>(emptyList())
    val persons: StateFlow<List<DomainPerson>>
        get() = _persons
    init{
        val memoryId = savedStateHandle.get<Int>("memoryId")
        getPersons()
        if (memoryId != null) {
            getMemory(memoryId)
        }
    }
    fun getPersons() {
        viewModelScope.launch {
            _persons.value = personUseCase.getPersons()
        }
    }

    fun getMemory(memoryId: Int) {
        viewModelScope.launch {
            _memory.value = memoryUseCase.getMemory(memoryId)
        }
    }

    fun getPerson(personId: Int) {
        viewModelScope.launch {
            _person.value = personUseCase.getPerson(personId)
        }
    }

    fun deleteMemory(memory: DomainMemory) {
        viewModelScope.launch {
            memoryUseCase.deleteMemory(memory)
        }
    }

    fun updateMemory(memory: DomainMemory) {
        viewModelScope.launch {
            memoryUseCase.updateMemory(memory)
            getMemory(memory.memoryId)
        }
    }

    fun clearPerson(){
        _person.value = null
    }

}