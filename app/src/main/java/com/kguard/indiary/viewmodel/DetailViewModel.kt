package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.PersonUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val useCase: PersonUseCase
): ViewModel() {
    private var _person = MutableLiveData<DomainPerson>()
    val person: LiveData<DomainPerson>
        get() = _person
    fun getPerson(person_id:Int)
    {
        viewModelScope.launch {
            _person.value=useCase.getPerson(person_id)
        }
    }
    fun deletePerson(person: DomainPerson)
    {
        viewModelScope.launch() {
            useCase.deletePerson(person)
        }
    }

}