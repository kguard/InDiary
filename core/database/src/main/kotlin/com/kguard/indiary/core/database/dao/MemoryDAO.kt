package com.kguard.indiary.core.database.dao

import androidx.room.*
import com.kguard.indiary.core.database.entity.Memory

@Dao
interface MemoryDAO {
    @Transaction
    @Query("SELECT * FROM Memory")
    suspend fun getMemories(): List<Memory>

    @Transaction
    @Query("SELECT * FROM Memory WHERE memoryId = :memoryId ")
    suspend fun getMemory(memoryId: Int): Memory

    @Query("SELECT * FROM Memory WHERE personId = :personId")
    suspend fun getPersonMemories(personId: Int): List<Memory>

    @Update
    suspend fun updateMemory(vararg memory: Memory)

    @Insert
    suspend fun insertMemory(vararg memory: Memory)

    @Delete
    suspend fun deleteMemory(vararg memory: Memory)
}