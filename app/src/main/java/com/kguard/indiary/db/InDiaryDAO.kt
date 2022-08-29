package com.kguard.indiary.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface PersonDAO {
    @Query("SELECT * FROM Person")
    fun getPersons(): LiveData<List<Person>>

    @Query("SELECT * FROM Person WHERE person_id LIKE:person_id ")
    fun getPerson(person_id:Int):LiveData<Person>

    @Update
    suspend fun updatePerson(vararg Person: Person)

    @Insert
    suspend fun insertPerson(vararg Person: Person)

    @Delete
    suspend fun deletePerson(vararg Person: Person)
}
@Dao
interface MemoryDAO {
    @Query("SELECT * FROM Memory")
    fun getMemories(): LiveData<List<Memory>>

    @Query("SELECT * FROM Memory WHERE memory_id LIKE:memory_id ")
    fun getMemory(memory_id:Int):LiveData<Memory>

    @Update
    suspend fun updateMemory(vararg Memory: Memory)

    @Insert
    suspend fun insertMemory(vararg Memory: Memory)

    @Delete
    suspend fun deleteMemory(vararg Memory: Memory)
}

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM Character")
    fun getCharacters(): LiveData<List<Character>>

    @Query("SELECT * FROM Character WHERE character_id LIKE:character_id ")
    fun getCharacter(character_id:Int):LiveData<Character>

    @Update
    suspend fun updateCharacter(vararg Character: Character)

    @Insert
    suspend fun insertCharacter(vararg Character: Character)

    @Delete
    suspend fun deleteCharacter(vararg Character: Character)
}

@Dao
interface TagDAO {
    @Query("SELECT * FROM Tag")
    fun getTags(): LiveData<List<Tag>>

    @Query("SELECT * FROM Tag WHERE tag_id LIKE:tag_id ")
    fun getTag(tag_id:Int):LiveData<Tag>

    @Update
    suspend fun updateTag(vararg Tag: Tag)

    @Insert
    suspend fun insertTag(vararg Tag: Tag)

    @Delete
    suspend fun deleteTag(vararg Tag: Tag)
}

@Dao
interface WithDAO {
    @Query("SELECT * FROM 'With'")
    fun getWiths(): LiveData<List<With>>

    @Query("SELECT * FROM 'With' WHERE with_id LIKE:with_id ")
    fun getWith(with_id:Int):LiveData<With>

    @Update
    suspend fun updateWith(vararg With: With)

    @Insert
    suspend fun insertWith(vararg With: With)

    @Delete
    suspend fun deleteWith(vararg With: With)
}


/*
@Dao
interface PersonTagDAO {
    @Transaction
    @Query("SELECT * FROM PERSON")
    suspend fun getPersonTag(): List<PersonTag>

    @Query("SELECT * FROM Person WHERE person_id LIKE:person_id ")
    suspend fun getPersonTag(person_id:Int):PersonTag

    @Update
    suspend fun updatePersonTag(vararg personTag: PersonTag)

    @Insert
    suspend fun insertPersonTag(vararg personTag: PersonTag)

    @Delete
    suspend fun deletePersonTag(vararg personTag: PersonTag)
}
@Dao
interface PersonCharacterDAO{
    @Transaction
    @Query("SELECT * FROM Person")
    suspend fun getPersonCharacter(): List<PersonCharacter>

    @Query("SELECT * FROM Person WHERE person_id LIKE:person_id ")
    suspend fun getPersonCharacter(person_id:Int):PersonCharacter

    @Update
    suspend fun updatePersonCharacter(vararg personCharacter: PersonCharacter)

    @Insert
    suspend fun insertPersonCharacter(vararg personCharacter: PersonCharacter)

    @Delete
    suspend fun deletePersonCharacter(vararg personCharacter: PersonCharacter)
}


@Dao
interface MemoryDAO {
    @Transaction
    @Query("SELECT * FROM Memory")
    suspend fun getMemory(): List<MemoryWith>

    @Query("SELECT * FROM Memory WHERE memory_id LIKE :memory_id")
    suspend fun getMemory(memory_id:Int):MemoryWith

    @Update
    suspend fun updateMemoryWith(vararg memoryWith: MemoryWith)

    @Insert
    suspend fun insertMemoryWith(vararg memoryWith: MemoryWith)

    @Delete
    suspend fun deleteMemoryWith(vararg memoryWith: MemoryWith)
}
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
}
@Dao
interface WithDAO {
    @Query("SELECT * FROM 'With'")
    suspend fun getWiths(): List<With>

    @Query("SELECT * FROM 'With' WHERE with_id LIKE:with_id ")
    suspend fun getWith(with_id:Int):With

    @Update
    suspend fun updateWith(vararg With: With)

    @Insert
    suspend fun insertWith(vararg With: With)

    @Delete
    suspend fun deleteWith(vararg With: With)
}*/



