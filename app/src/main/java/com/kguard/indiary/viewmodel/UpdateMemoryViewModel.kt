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


    private var _photos = ListLiveData<String>()
    val photos: LiveData<ArrayList<String>>
        get() = _photos



    fun setPhoto(uri: String)
    {
        _photos.add(uri)
    }

    fun removePhotoByPosition(position: Int) {
        _photos.removeAt(position)
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



    fun getPerson(person_id: Int) {
        viewModelScope.launch {
            _person.value = PersonUseCase.getPerson(person_id)
        }
    }
}