package com.kguard.indiary.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.kguard.indiary.db.InDiaryDB
import com.kguard.indiary.db.Person
import com.kguard.indiary.repository.PersonRepository
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) :AndroidViewModel(application){
    val personAll:LiveData<List<Person>>?
    val repository:PersonRepository
    init {
        val personDao= InDiaryDB.getInstance(application)?.personDao()
        repository=PersonRepository(personDao)
        personAll = repository.person
    }
    fun getPerson(person_id:Int): LiveData<Person>?
    {
        return repository.getPerson(person_id)
    }
    fun insertPerson(person: Person)
    {
        viewModelScope.launch {
            repository.insertPerson(person)
        }
    }
    fun updatePerson(person: Person)
    {
        viewModelScope.launch {
            repository.updatePerson(person)
        }
    }
    fun deletePerson(person:Person)
    {
        viewModelScope.launch {
            repository.deletePerson(person)
        }
    }
}