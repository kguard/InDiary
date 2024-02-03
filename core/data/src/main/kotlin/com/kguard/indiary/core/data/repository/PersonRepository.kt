package com.kguard.indiary.core.data.repository

import com.kguard.indiary.core.model.DomainPerson

interface PersonRepository {
    suspend fun getPersons(): List<DomainPerson>
    suspend fun getPersonsName(): List<String>
    suspend fun getPerson(personId: Int): DomainPerson
    suspend fun getPersonId(name: String): Int
    suspend fun updatePerson(person: DomainPerson)
    suspend fun insertPerson(person: DomainPerson)
    suspend fun deletePerson(person: DomainPerson)
}