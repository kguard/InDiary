package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.domain.DomainMemory
import com.kguard.indiary.usecase.MemoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoryViewModel @Inject constructor(
    private val useCase: MemoryUseCase
):ViewModel() {
    private var _memory: MutableLiveData<List<DomainMemory>> = MutableLiveData<List<DomainMemory>>()
    val memory: LiveData<List<DomainMemory>>
    get() = _memory
    fun getMemories(){
        viewModelScope.launch() {
            _memory.postValue(useCase.getMemories())
        }
    }
}