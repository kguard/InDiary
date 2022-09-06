package com.kguard.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kguard.data.local.entity.Character

@Dao
interface CharacterDAO {
    @Transaction
    @Query("SELECT * FROM Character")
    suspend fun getCharacters(): List<Character>

    @Query("SELECT * FROM Character WHERE character_id = :character_id ")
    suspend fun getCharacter(character_id:Int): Character

    @Query("SELECT * FROM Character WHERE person_id = :person_id ")
    suspend fun getPersonCharacters(person_id:Int): List<Character>

    @Update
    suspend fun updateCharacter(vararg Character: Character)

    @Insert
    suspend fun insertCharacter(vararg Character: Character)

    @Delete
    suspend fun deleteCharacter(vararg Character: Character)
}
