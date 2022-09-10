package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.data.local.entity.Memory
import com.kguard.domain.domain.DomainMemory
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.CharacterUseCase
import com.kguard.indiary.usecase.MemoryUseCase
import com.kguard.indiary.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemoryViewModel @Inject constructor(
    private val MemoryUseCase: MemoryUseCase,
    private val PersonUseCase: PersonUseCase
) : ViewModel() {
    private var _personName = MutableLiveData<List<String>>()
    val personName: LiveData<List<String>>
        get() = _personName

    private var _persons= MutableLiveData<List<DomainPerson>>()
    val persons: LiveData<List<DomainPerson>>
        get() = _persons

    private var _person = MutableLiveData<Int>()
    val person: LiveData<Int>
        get() = _person

    init {
        getPersonsName()
        getPersons()
    }

    fun insertMemory(memory: DomainMemory) {
        viewModelScope.launch {
            MemoryUseCase.insertMemory(memory)
        }
    }

    fun getPersons(){
        viewModelScope.launch {
            _persons.value=PersonUseCase.getPersons()
        }
    }
    fun getPersonsName() {
        viewModelScope.launch {
            _personName.value = PersonUseCase.getPersonsName()
        }
    }

    fun getPersonId(name: String) {
        viewModelScope.launch {
            _person.value=PersonUseCase.getPersonId(name)
        }
    }
}