package com.kguard.domain.repository

import com.kguard.domain.domain.DomainMemory
import com.kguard.domain.domain.DomainPerson

interface MemoryRepository {
    suspend fun getMemories():List<DomainMemory>
    suspend fun getMemory(memory_id:Int): DomainMemory
    suspend fun getPersonMemories(person_id:Int):List<DomainMemory>
    suspend fun updateMemory(memory: DomainMemory)
    suspend fun insertMemory(memory: DomainMemory)
    suspend fun deleteMemory(memory: DomainMemory)
}