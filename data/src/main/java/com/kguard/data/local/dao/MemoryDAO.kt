package com.kguard.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kguard.data.local.entity.Memory

@Dao
interface MemoryDAO {
    @Transaction
    @Query("SELECT * FROM Memory")
    suspend fun getMemories(): List<Memory>

    @Transaction
    @Query("SELECT * FROM Memory WHERE memory_id =:memory_id ")
    suspend fun getMemory(memory_id:Int): Memory

    @Update
    suspend fun updateMemory(vararg Memory: Memory)

    @Insert
    suspend fun insertMemory(vararg Memory: Memory)

    @Delete
    suspend fun deleteMemory(vararg Memory: Memory)
}