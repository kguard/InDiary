package com.kguard.indiary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.model.DomainMemory
import com.kguard.domain.usecase.MemoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun clearMemories(){
        viewModelScope.launch {
            _memory.value= emptyList()
        }
    }
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