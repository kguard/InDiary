package com.kguard.indiary.usecase

import com.kguard.domain.domain.DomainPerson
import com.kguard.domain.repository.PersonRepository
import javax.inject.Inject

class PersonUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    suspend fun getPersons():List<DomainPerson>
    {
        return repository.getPersons()
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
        return repository.insertPerson(person)
    }
    suspend fun deletePerson(person: DomainPerson)
    {
        return repository.deletePerson(person)
    }
}