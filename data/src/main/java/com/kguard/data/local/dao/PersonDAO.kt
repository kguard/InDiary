package com.kguard.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kguard.data.local.entity.Person

@Dao
interface PersonDAO {
    @Transaction
    @Query("SELECT * FROM Person")
    suspend fun getPersons(): List<Person>

    @Transaction
    @Query("SELECT * FROM Person WHERE person_id =:person_id ")
    suspend fun getPerson(person_id:Int): Person

    @Update
    suspend fun updatePerson(vararg Person: Person)

    @Insert
    suspend fun insertPerson(vararg Person: Person)

    @Delete
    suspend fun deletePerson(vararg Person: Person)
}