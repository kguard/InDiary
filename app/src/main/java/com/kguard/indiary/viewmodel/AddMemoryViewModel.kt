package com.kguard.indiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.data.local.entity.Memory
import com.kguard.domain.domain.DomainMemory
import com.kguard.indiary.usecase.CharacterUseCase
import com.kguard.indiary.usecase.MemoryUseCase
import com.kguard.indiary.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemoryViewModel@Inject constructor(
    private val useCase: MemoryUseCase
): ViewModel() {
    fun insertMemory(memory: DomainMemory)
    {
        viewModelScope.launch {
            useCase.insertMemory(memory)
        }
    }
}