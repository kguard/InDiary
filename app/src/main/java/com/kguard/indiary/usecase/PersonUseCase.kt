package com.kguard.indiary.usecase

import com.kguard.domain.domain.DomainPerson
import com.kguard.domain.domain.InvalidPersonException
import com.kguard.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend fun getPersons(): List<DomainPerson>
    {
        return repository.getPersons().sortedWith(compareByDescending<DomainPerson> { (it.favorite)  }.thenBy { it.name })
    }
    suspend fun getPersonId(name:String):Int
    {
        return repository.getPersonId(name)
    }
    suspend fun getPersonsName():List<String>
    {
        return repository.getPersonsName()
    }
    suspend fun getPerson(person_id:Int): DomainPerson
    {
        return repository.getPerson(person_id)
    }
    suspend fun updatePerson(person: DomainPerson)
    {
        return repository.updatePerson(person)
    }
    suspend fun insertPerson(person: DomainPerson)
    {
        if(person.name.isBlank()) throw  InvalidPersonException("이름을 입력해 주세요")
        return repository.insertPerson(person)
    }
    suspend fun deletePerson(person: DomainPerson)
    {
        return repository.deletePerson(person)
    }
}