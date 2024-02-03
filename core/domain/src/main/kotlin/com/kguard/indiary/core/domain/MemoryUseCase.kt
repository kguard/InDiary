package com.kguard.indiary.core.domain

import com.kguard.indiary.core.data.repository.MemoryRepository
import com.kguard.indiary.core.model.DomainMemory
import javax.inject.Inject

class MemoryUseCase @Inject constructor(
    private val repository: MemoryRepository
) {
    suspend fun getMemories(): List<DomainMemory> {
        return repository.getMemories()
    }

    suspend fun getPersonMemories(personId: Int): List<DomainMemory> {
        return repository.getPersonMemories(personId)
    }

    suspend fun getMemory(memoryId: Int): DomainMemory {
        return repository.getMemory(memoryId)
    }

    suspend fun updateMemory(memory: DomainMemory) {
        return repository.updateMemory(memory)
    }

    suspend fun insertMemory(memory: DomainMemory) {
        return repository.insertMemory(memory)
    }

    suspend fun deleteMemory(memory: DomainMemory) {
        return repository.deleteMemory(memory)
    }
}