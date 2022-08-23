package com.kguard.indiary.db

import androidx.room.*

@Dao
interface PersonDAO {
    @Query("SELECT * FROM Person")
    fun getAll(): List<Person>

    @Query("SELECT * FROM Person WHERE person_id LIKE:person_id ")
    fun getPerson(person_id:Int):Person

    @Update
    fun updatePerson(vararg person: Person)

    @Insert
    fun insertPerson(vararg person: Person)

    @Delete
    fun deletePerson(vararg person: Person)
}

@Dao
interface MemoryDAO {
    @Query("SELECT * FROM Memory")
    fun getAll(): List<Memory>

    @Query("SELECT * FROM Memory WHERE memory_id LIKE :memory_id")
    fun getMemory(memory_id:Int):Memory

    @Update
    fun updateMemory(vararg memory: Memory)

    @Insert
    fun insertMemory(vararg memory: Memory)

    @Delete
    fun deleteMemory(vararg memory: Memory)
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
interface ImageDAO {
    @Query("SELECT * FROM Image")
    fun getAll(): List<Image>

    @Query("SELECT * FROM Image WHERE image_id LIKE :image_id")
    fun getImage(image_id:Int):Image

    @Update
    fun updateImage(vararg image: Image)

    @Insert
    fun insertImage(vararg image: Image)

    @Delete
    fun deleteImage(vararg image: Image)
}

