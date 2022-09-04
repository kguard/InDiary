package com.kguard.indiary.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val useCase: PersonUseCase
):ViewModel(){
    private var _persons: MutableLiveData<List<DomainPerson>> = MutableLiveData<List<DomainPerson>>()
    val persons: MutableLiveData<List<DomainPerson>>
    get() = _persons
    init{
        viewModelScope.launch {
            _persons.value=useCase.getPersons()
        }
    }
}