package com.kguard.domain.repository

import com.kguard.domain.domain.DomainPerson

interface PersonRepository {
    suspend fun getPersons():List<DomainPerson>
    suspend fun getPerson(person_id:Int):DomainPerson
    suspend fun updatePerson(person: DomainPerson)
    suspend fun insertPerson(person: DomainPerson)
    suspend fun deletePerson(person: DomainPerson)
}