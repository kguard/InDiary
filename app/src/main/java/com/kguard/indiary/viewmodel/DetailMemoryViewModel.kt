package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.domain.DomainMemory
import com.kguard.indiary.usecase.MemoryUseCase
import com.kguard.indiary.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMemoryViewModel @Inject constructor(
    private val memoryUseCase: MemoryUseCase,
) : ViewModel() {
    private var _memory =  MutableStateFlow<List<DomainMemory>>(emptyList())
    val memory: StateFlow<List<DomainMemory>>
        get() = _memory

    fun getMemory(person_id: Int) {
        viewModelScope.launch {
            _memory.value = memoryUseCase.getPersonMemories(person_id)
        }
    }
    fun deleteMemory(memory: DomainMemory)
    {
        viewModelScope.launch {
            memoryUseCase.deleteMemory(memory)
        }
    }
}