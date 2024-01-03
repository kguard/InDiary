package com.kguard.indiary.feature.person.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.indiary.core.domain.MemoryUseCase
import com.kguard.indiary.core.domain.PersonUseCase
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.model.DomainPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class DetailPersonViewModel @Inject constructor(
    private val personUseCase: PersonUseCase,
    private val memoryUseCase: MemoryUseCase
) : ViewModel() {
    private var _person = MutableLiveData<DomainPerson>()
    val person: LiveData<DomainPerson>
        get() = _person

    private val _memories = MutableLiveData<List<DomainMemory>>()
    val memories: LiveData<List<DomainMemory>>
        get() = _memories

    fun getMemoriesInPerson(person_id: Int) {
        viewModelScope.launch {
            _memories.value = memoryUseCase.getPersonMemories(person_id)
        }
    }

    fun getPerson(person_id: Int) {
        viewModelScope.launch {
            _person.value = personUseCase.getPerson(person_id)
        }
    }

    fun deletePerson(person: DomainPerson) {
        viewModelScope.launch {
            personUseCase.deletePerson(person)
        }
    }

    fun deleteMemory(memory: DomainMemory) {
        viewModelScope.launch {
            memoryUseCase.deleteMemory(memory)
        }
    }

    fun getAge(string: String): String {
        var result = "?"
        val pattern = Pattern.matches(
            "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\$",
            string
        )
        if (pattern) {
            val now = LocalDate.now()
            val parsedBirthDate = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyyMMdd"))
            var age = now.minusYears(parsedBirthDate.year.toLong()).year
            if (parsedBirthDate.plusYears(age.toLong()).isAfter(now)) {
                age -= 1
            }
            result = age.toString()
        } else {
            result = "?"
        }
        return result
    }
}