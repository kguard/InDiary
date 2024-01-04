package com.kguard.indiary.feature.memory.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.indiary.core.common.ListLiveData
import com.kguard.indiary.core.domain.MemoryUseCase
import com.kguard.indiary.core.domain.PersonUseCase
import com.kguard.indiary.core.model.DomainMemory

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemoryViewModel @Inject constructor(
    private val MemoryUseCase: MemoryUseCase,
    private val PersonUseCase: PersonUseCase
) : ViewModel() {

    private var _person = MutableLiveData<Int>()
    val person: LiveData<Int>
        get() = _person

    private var _photos = ListLiveData<String>()
    val photos: LiveData<ArrayList<String>>
        get() = _photos


    fun setPhoto(uri: String) {
        _photos.add(uri)
    }

    fun removePhotoByPosition(position: Int) {
        _photos.removeAt(position)
    }

    fun insertMemory(memory: DomainMemory) {
        viewModelScope.launch {
            MemoryUseCase.insertMemory(memory)
        }
    }


}