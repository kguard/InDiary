package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.domain.DomainMemory
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.MemoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoryViewModel @Inject constructor(
    private val useCase: MemoryUseCase
):ViewModel() {
    private var _memory = MutableStateFlow<List<DomainMemory>>(emptyList())
    val memory: StateFlow<List<DomainMemory>>
        get() = _memory
    fun getMemories(){
        viewModelScope.launch() {
            _memory.value=useCase.getMemories()
        }
    }
    fun deleteMemory(memory: DomainMemory)
    {
        viewModelScope.launch {
            useCase.deleteMemory(memory)
            getMemories()
        }
    }
}