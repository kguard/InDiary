package com.kguard.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kguard.data.local.entity.With

@Dao
interface WithDAO {
    @Transaction
    @Query("SELECT * FROM 'With'")
    suspend fun getWiths(): List<With>

    @Transaction
    @Query("SELECT * FROM 'With' WHERE with_id =:with_id ")
    suspend fun getWith(with_id:Int): With

    @Query("SELECT * FROM 'With' WHERE memory_id=:memory_id")
    suspend fun getMemoryWiths(memory_id:Int) :List<With>

    @Update
    suspend fun updateWith(vararg With: With)

    @Insert
    suspend fun insertWith(vararg With: With)

    @Delete
    suspend fun deleteWith(vararg With: With)
}