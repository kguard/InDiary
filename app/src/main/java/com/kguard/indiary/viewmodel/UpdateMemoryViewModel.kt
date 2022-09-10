package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.domain.DomainMemory
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.MemoryUseCase
import com.kguard.indiary.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateMemoryViewModel @Inject constructor(
    private val memoryUseCase: MemoryUseCase,
    private val PersonUseCase: PersonUseCase
) : ViewModel() {
    private var _memory = MutableLiveData<DomainMemory>()
    val memory: LiveData<DomainMemory>
        get() = _memory

    private var _person = MutableLiveData<DomainPerson>()
    val person: LiveData<DomainPerson>
        get() = _person

    private var _personId = MutableLiveData<Int>()
    val personId: LiveData<Int>
        get() = _personId

    private var _personName = MutableLiveData<List<String>>()
    val personName: LiveData<List<String>>
        get() = _personName



    init {
        getPersonsName()
    }

    fun getPersonsName() {
        viewModelScope.launch {
            _personName.value = PersonUseCase.getPersonsName()
        }
    }

    fun getMemory(memory_id: Int) {
        viewModelScope.launch {
            _memory.value = memoryUseCase.getMemory(memory_id)
        }
    }

    fun updateMemory(memory: DomainMemory) {
        viewModelScope.launch {
            memoryUseCase.updateMemory(memory)
        }
    }

    fun getPersonId(name: String) {
        viewModelScope.launch {
            _personId.value = PersonUseCase.getPersonId(name)
        }
    }

    fun getPerson(person_id: Int) {
        viewModelScope.launch {
            _person.value = PersonUseCase.getPerson(person_id)
        }
    }
}