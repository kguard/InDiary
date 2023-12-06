package com.kguard.indiary.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.indiary.core.domain.PersonUseCase
import com.kguard.indiary.core.model.DomainPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val personUseCase: PersonUseCase
) : ViewModel() {
    //    private val _customCharacter= MutableLiveData<CustomCharacter>()
//    val customCharacter: LiveData<CustomCharacter>
//    get() = _customCharacter
//    fun setCustomCharacter(customCharacter: CustomCharacter){
//        _customCharacter.value=customCharacter
//    }
    private val _person = MutableLiveData<DomainPerson?>()
    val person: MutableLiveData<DomainPerson?>
        get() = _person

    private val _persons = MutableLiveData<List<DomainPerson>>()
    val persons: LiveData<List<DomainPerson>>
        get() = _persons

    private val _personId = MutableLiveData<Int>()
    val personId: LiveData<Int>
        get() = _personId

    fun clearPerson() {
        _person.value = null
    }

    fun setPerson(person: DomainPerson) {
        _person.value = person
    }

    fun getPersons() {
        viewModelScope.launch { _persons.value = personUseCase.getPersons() }

    }

    fun setPersonId(personId: Int) {
        viewModelScope.launch { _personId.value = personId }
    }

    fun clearPersonId() {
        viewModelScope.launch { _personId.value = -1 }
    }

}