package com.kguard.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kguard.data.local.entity.Tag

@Dao
interface TagDAO {
    @Transaction
    @Query("SELECT * FROM Tag")
    suspend fun getTags(): List<Tag>

    @Transaction
    @Query("SELECT * FROM Tag WHERE tag_id =:tag_id ")
    suspend fun getTag(tag_id:Int): Tag

    @Query("SELECT * FROM Tag WHERE person_id =:person_id")
    suspend fun getPersonTags(person_id:Int): List<Tag>

    @Update
    suspend fun updateTag(vararg Tag: Tag)

    @Insert
    suspend fun insertTag(vararg Tag: Tag)

    @Delete
    suspend fun deleteTag(vararg Tag: Tag)
}