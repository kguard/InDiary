package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.domain.DomainMemory
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.MemoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailMemory2ViewModel @Inject constructor(
    private val useCase: MemoryUseCase
):ViewModel() {
    private var _memory: MutableLiveData<DomainMemory> = MutableLiveData<DomainMemory>()
    val memory: LiveData<DomainMemory>
    get() =_memory
    fun getMemory(memory_id:Int)
    {
        viewModelScope.launch {
            _memory.value=useCase.getMemory(memory_id)
        }
    }
    fun deleteMemory(memory: DomainMemory)
    {
        viewModelScope.launch {
            useCase.deleteMemory(memory)
        }
    }
}