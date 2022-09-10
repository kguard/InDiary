package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.data.CustomCharacter
import com.kguard.indiary.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    private val _person=MutableLiveData<DomainPerson>()
    val person : LiveData<DomainPerson>
    get() = _person

    private val _persons=MutableLiveData<List<DomainPerson>>()
    val persons : LiveData<List<DomainPerson>>
        get() = _persons

    fun setPerson(person: DomainPerson)
    {
        _person.value=person
    }
    fun getPersons()
    {
        viewModelScope.launch { _persons.value=personUseCase.getPersons()  }

    }

}