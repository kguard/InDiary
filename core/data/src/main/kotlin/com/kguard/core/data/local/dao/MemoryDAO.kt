package com.kguard.core.data.local.dao

import androidx.room.*
import com.kguard.core.data.local.entity.Memory

@Dao
interface MemoryDAO {
    @Transaction
    @Query("SELECT * FROM Memory")
    suspend fun getMemories(): List<Memory>
    @Transaction
    @Query("SELECT * FROM Memory WHERE memory_id =:memory_id ")
    suspend fun getMemory(memory_id:Int): Memory

    @Query("SELECT * FROM Memory WHERE person_id = :person_id")
    suspend fun getPersonMemories(person_id:Int): List<Memory>

    @Update
    suspend fun updateMemory(vararg Memory: Memory)

    @Insert
    suspend fun insertMemory(vararg Memory: Memory)

    @Delete
    suspend fun deleteMemory(vararg Memory: Memory)
}