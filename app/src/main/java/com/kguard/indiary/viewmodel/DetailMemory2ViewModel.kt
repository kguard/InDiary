package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.domain.DomainMemory
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.MemoryUseCase
import com.kguard.indiary.usecase.PersonUseCase
import com.kguard.indiary.util.ListLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMemory2ViewModel @Inject constructor(
    private val memoryUseCase: MemoryUseCase,
    private val personUseCase: PersonUseCase
) : ViewModel() {
    private var _memory: MutableLiveData<DomainMemory> = MutableLiveData<DomainMemory>()
    val memory: LiveData<DomainMemory>
        get() = _memory

    private var _person = MutableLiveData<DomainPerson>()
    val person: LiveData<DomainPerson>
        get() = _person

    private var _photos = ListLiveData<String>()
    val photos: LiveData<ArrayList<String>>
        get() = _photos

    fun setPhoto(uri: String)
    {
        _photos.add(uri)
    }

    fun getMemory(memory_id: Int) {
        viewModelScope.launch {
            _memory.value = memoryUseCase.getMemory(memory_id)
        }
    }

    fun getPerson(person_id: Int) {
        viewModelScope.launch {
            _person.value = personUseCase.getPerson(person_id)
        }
    }

    fun deleteMemory(memory: DomainMemory) {
        viewModelScope.launch {
            memoryUseCase.deleteMemory(memory)
        }
    }
}