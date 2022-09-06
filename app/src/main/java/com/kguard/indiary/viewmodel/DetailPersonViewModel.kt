package com.kguard.indiary.viewmodel

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
    private var _person: MutableLiveData<DomainPerson> = MutableLiveData<DomainPerson>()
    val person: MutableLiveData<DomainPerson>
        get() = _person
    fun getPerson(person_id:Int)
    {
        viewModelScope.launch {
            _person.postValue(useCase.getPerson(person_id))
        }
    }
    fun getAge(string:String): Int {
        val now= LocalDate.now()
        val parsedBirthDate = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyyMMdd"))
        var age=now.minusYears(parsedBirthDate.year.toLong()).year
        if(parsedBirthDate.plusYears(age.toLong()).isAfter(now)){
            age -= 1
        }
        return age
    }
}