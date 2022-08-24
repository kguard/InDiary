package com.kguard.indiary.db

import androidx.room.*

@Dao
interface PersonTagDAO {
    @Transaction
    @Query("SELECT * FROM PERSON")
    fun getPersonTag(): List<PersonTag>

    @Query("SELECT * FROM Person WHERE person_id LIKE:person_id ")
    fun getPersonTag(person_id:Int):PersonTag

    @Update
    fun updatePersonTag(vararg personTag: PersonTag)

    @Insert
    fun insertPersonTag(vararg personTag: PersonTag)

    @Delete
    fun deletePersonTag(vararg personTag: PersonTag)
}
@Dao
interface PersonCharacterDAO{
    @Transaction
    @Query("SELECT * FROM Person")
    fun getPersonCharacter(): List<PersonCharacter>

    @Query("SELECT * FROM Person WHERE person_id LIKE:person_id ")
    fun getPersonCharacter(person_id:Int):PersonCharacter

    @Update
    fun updatePersonCharacter(vararg personCharacter: PersonCharacter)

    @Insert
    fun insertPersonCharacter(vararg personCharacter: PersonCharacter)

    @Delete
    fun deletePersonCharacter(vararg personCharacter: PersonCharacter)
}


@Dao
interface MemoryDAO {
    @Transaction
    @Query("SELECT * FROM Memory")
    fun getMemory(): List<MemoryWith>

    @Query("SELECT * FROM Memory WHERE memory_id LIKE :memory_id")
    fun getMemory(memory_id:Int):MemoryWith

    @Update
    fun updateMemoryWith(vararg memoryWith: MemoryWith)

    @Insert
    fun insertMemoryWith(vararg memoryWith: MemoryWith)

    @Delete
    fun deleteMemoryWith(vararg memoryWith: MemoryWith)
}
/*
@Dao
interface CharacterDAO {
    @Query("SELECT * FROM Character")
    fun getAll(): List<Character>

    @Query("SELECT * FROM Character WHERE character_id LIKE :character_id")
    fun getCharacter(character_id:Int):Character

    @Update
    fun updateCharacter(vararg character: Character)

    @Insert
    fun insertCharacter(vararg character: Character)

    @Delete
    fun deleteCharacter(vararg character: Character)
}

@Dao
interface TagDAO {
    @Query("SELECT * FROM Tag")
    fun getAll(): List<Tag>

    @Query("SELECT * FROM Tag WHERE tag_id LIKE :tag_id")
    fun getTag(tag_id:Int):Tag

    @Update
    fun updateTag(vararg tag: Tag)

    @Insert
    fun insertTag(vararg tag: Tag)

    @Delete
    fun deleteTag(vararg tag: Tag)
}*/



