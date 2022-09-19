package com.kguard.indiary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.domain.domain.DomainPerson
import com.kguard.indiary.usecase.PersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DetailPersonViewModel @Inject constructor(
    private val useCase: PersonUseCase
):ViewModel() {
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
        viewModelScope.launch {
            useCase.deletePerson(person)
        }
    }
    fun getAge(string:String?): String {
        var result="?"
        if(string != null) {
            val now = LocalDate.now()
            val parsedBirthDate = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyyMMdd"))
            var age = now.minusYears(parsedBirthDate.year.toLong()).year
            if (parsedBirthDate.plusYears(age.toLong()).isAfter(now)) {
                age -= 1
            }
            result=age.toString()
        }
        else if (string == null)
        {
            result="?"
        }
        return result
    }
}