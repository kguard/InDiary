package com.kguard.indiary.feature.memory.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.indiary.core.common.ListLiveData
import com.kguard.indiary.core.domain.MemoryUseCase
import com.kguard.indiary.core.domain.PersonUseCase
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.model.DomainPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoryEditViewModel @Inject constructor(
    private val memoryUseCase: MemoryUseCase,
    private val personUseCase: PersonUseCase
) : ViewModel() {
    private var _memory = MutableLiveData<DomainMemory>()
    val memory: LiveData<DomainMemory>
        get() = _memory

    private var _person = MutableLiveData<DomainPerson>()
    val person: LiveData<DomainPerson>
        get() = _person

    private val _persons = MutableLiveData<List<DomainPerson>>()
    val persons: LiveData<List<DomainPerson>>
        get() = _persons

    private var _photos = ListLiveData<String>()
    val photos: LiveData<ArrayList<String>>
        get() = _photos

    fun setPhoto(uri: String) {
        _photos.add(uri)
    }

    fun removePhotoByPosition(position: Int) {
        _photos.removeAt(position)
    }


    fun getMemory(memoryId: Int) {
        viewModelScope.launch {
            _memory.value = memoryUseCase.getMemory(memoryId)
        }
    }

    fun updateMemory(memory: DomainMemory) {
        viewModelScope.launch {
            memoryUseCase.updateMemory(memory)
        }
    }

    fun insertMemory(memory: DomainMemory) {
        viewModelScope.launch {
            memoryUseCase.insertMemory(memory)
        }
    }

    fun getPerson(personId: Int) {
        viewModelScope.launch {
            _person.value = personUseCase.getPerson(personId)
        }
    }

    fun getPersons() {
        viewModelScope.launch {
            _persons.value = personUseCase.getPersons()
        }
    }
}