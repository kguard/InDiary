package com.kguard.indiary.core.domain

import com.kguard.indiary.core.data.repository.PersonRepository
import com.kguard.indiary.core.model.DomainPerson
import com.kguard.indiary.core.model.InvalidPersonException
import javax.inject.Inject

class PersonUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend fun getPersons(): List<DomainPerson> {
        return repository.getPersons()
            .sortedWith(compareByDescending<DomainPerson> { (it.favorite) }.thenBy { it.name })
    }

    suspend fun getPerson(personId: Int): DomainPerson {
        return repository.getPerson(personId)
    }

    suspend fun updatePerson(person: DomainPerson) {
        return repository.updatePerson(person)
    }

    suspend fun insertPerson(person: DomainPerson) {
        if (person.name.isBlank()) throw InvalidPersonException("이름을 입력해 주세요")
        return repository.insertPerson(person)
    }

    suspend fun deletePerson(person: DomainPerson) {
        return repository.deletePerson(person)
    }
}