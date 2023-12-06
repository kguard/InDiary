package com.kguard.indiary.core.data.repository

import com.kguard.indiary.core.model.DomainMemory

interface MemoryRepository {
    suspend fun getMemories(): List<DomainMemory>
    suspend fun getMemory(memory_id: Int): DomainMemory
    suspend fun getPersonMemories(person_id: Int): List<DomainMemory>
    suspend fun updateMemory(memory: DomainMemory)
    suspend fun insertMemory(memory: DomainMemory)
    suspend fun deleteMemory(memory: DomainMemory)
}