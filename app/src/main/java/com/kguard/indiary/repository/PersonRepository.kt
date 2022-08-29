package com.kguard.indiary.repository

import android.app.Application
import com.kguard.indiary.db.InDiaryDB
import com.kguard.indiary.db.Person
import com.kguard.indiary.db.PersonDAO

class PersonRepository(private val personDao: PersonDAO?) {
    val person=personDao?.getPersons()
    suspend fun insertPerson(person: Person)
    {
        personDao?.insertPerson(person)
    }
    suspend fun updatePerson(person: Person)
    {
        personDao?.updatePerson(person)
    }
    suspend fun deletePerson(person:Person)
    {
        personDao?.deletePerson()
    }
}