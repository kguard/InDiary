package com.kguard.core.data.local.dao

import androidx.room.*
import com.kguard.core.data.local.entity.Person

@Dao
interface PersonDAO {
    @Query("SELECT * FROM Person")
    suspend fun getPersons(): List<Person>

    @Query("SELECT * FROM Person WHERE person_id =:person_id ")
    suspend fun getPerson(person_id:Int): Person

    @Query("SELECT person_id FROM Person WHERE PersonName LIKE :PersonName ")
    suspend fun getPersonId(PersonName:String): Int

    @Update
    suspend fun updatePerson(vararg Person: Person)

    @Insert
    suspend fun insertPerson(vararg Person: Person)

    @Delete
    suspend fun deletePerson(vararg Person: Person)
}