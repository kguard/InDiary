package com.kguard.indiary.core.database.dao

import androidx.room.*
import com.kguard.indiary.core.database.entity.Person

@Dao
interface PersonDAO {
    @Query("SELECT * FROM Person")
    suspend fun getPersons(): List<Person>

    @Query("SELECT * FROM Person WHERE personId = :personId ")
    suspend fun getPerson(personId: Int): Person

    @Query("SELECT personId FROM Person WHERE PersonName LIKE :personName ")
    suspend fun getPersonId(personName: String): Int

    @Update
    suspend fun updatePerson(vararg person: Person)

    @Insert
    suspend fun insertPerson(vararg person: Person)

    @Delete
    suspend fun deletePerson(vararg person: Person)
}