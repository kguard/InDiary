package com.kguard.indiary.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.kguard.indiary.db.InDiaryDB
import com.kguard.indiary.db.Person
import com.kguard.indiary.db.PersonDAO

class PersonRepository(private val personDao: PersonDAO?) {
    val person=personDao?.getPersons()
    fun getPerson(person_id:Int): LiveData<Person>?
    {
       return personDao?.getPerson(person_id)
    }
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