package com.kguard.indiary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.indiary.core.domain.MemoryUseCase
import com.kguard.indiary.core.model.DomainMemory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMemoryViewModel @Inject constructor(
    private val memoryUseCase: MemoryUseCase,
) : ViewModel() {
    private var _memory = MutableStateFlow<List<DomainMemory>>(emptyList())
    val memory: StateFlow<List<DomainMemory>>
        get() = _memory

    fun clearMemories() {
        viewModelScope.launch {
            _memory.value = emptyList()
        }
    }

    fun getMemory(person_id: Int) {
        viewModelScope.launch {
            _memory.value = memoryUseCase.getPersonMemories(person_id)
        }
    }

    fun deleteMemory(memory: DomainMemory, person_id: Int) {
        viewModelScope.launch {
            memoryUseCase.deleteMemory(memory)
            getMemory(person_id)
        }
    }
}