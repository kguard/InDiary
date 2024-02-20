package com.kguard.indiary.feature.person.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kguard.indiary.core.domain.MemoryUseCase
import com.kguard.indiary.core.domain.PersonUseCase
import com.kguard.indiary.core.model.DomainMemory
import com.kguard.indiary.core.model.DomainPerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(
    private val personUseCase: PersonUseCase,
    private val memoryUseCase: MemoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _person = MutableStateFlow(DomainPerson())
    val person: StateFlow<DomainPerson>
        get() = _person

    private val _memories = MutableStateFlow<List<DomainMemory>>(emptyList())
    val memories: StateFlow<List<DomainMemory>>
        get() = _memories

    init {
        val personId = savedStateHandle.get<Int>("personId")
        if (personId != null) {
            getPerson(personId)
            getMemoriesInPerson(personId)
        }
    }

    fun getMemoriesInPerson(personId: Int) {
        viewModelScope.launch {
            _memories.value = memoryUseCase.getPersonMemories(personId)
        }
    }

    fun getPerson(personId: Int) {
        viewModelScope.launch {
            _person.value = personUseCase.getPerson(personId)
        }
    }

    fun deletePerson(person: DomainPerson) {
        viewModelScope.launch {
            personUseCase.deletePerson(person)
        }
    }

    fun updatePerson(person: DomainPerson) {
        viewModelScope.launch {
            personUseCase.updatePerson(person)
            getPerson(person.personId)
        }
    }

    fun deleteMemory(memory: DomainMemory) {
        viewModelScope.launch {
            memoryUseCase.deleteMemory(memory)
            getMemoriesInPerson(person.value.personId)
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