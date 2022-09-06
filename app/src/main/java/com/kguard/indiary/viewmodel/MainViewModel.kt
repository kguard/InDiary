package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kguard.indiary.data.CustomCharacter
import com.kguard.indiary.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val personUseCase: PersonUseCase,
    private val memoryUseCase: MemoryUseCase,
    private val characterUseCase: CharacterUseCase,
    private val tagUseCase: TagUseCase,
    private val withUseCase: WithUseCase
):ViewModel(){
//    private val _customCharacter= MutableLiveData<CustomCharacter>()
//    val customCharacter: LiveData<CustomCharacter>
//    get() = _customCharacter
//    fun setCustomCharacter(customCharacter: CustomCharacter){
//        _customCharacter.value=customCharacter
//    }

}