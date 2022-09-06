package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.data.local.entity.Character
import com.kguard.domain.domain.DomainCharacter
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.CharacterUseCase
import com.kguard.indiary.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPersonViewModel @Inject constructor(
    private val PersonUseCase: PersonUseCase,
    private val CharacterUseCase: CharacterUseCase
):ViewModel(){
    fun insertPerson(person:DomainPerson)
    {
        viewModelScope.launch() {
            PersonUseCase.insertPerson(person)
        }
    }

}