package com.kguard.core.domain.usecase

import com.kguard.core.domain.model.DomainMemory
import com.kguard.core.domain.repository.MemoryRepository

class MemoryUseCase (
    private val repository: MemoryRepository
) {
    suspend fun getMemories():List<DomainMemory>
    {
        return repository.getMemories()
    }
    suspend fun getPersonMemories(person_id:Int):List<DomainMemory>
    {
        return repository.getPersonMemories(person_id)
    }
    suspend fun getMemory(memory_id:Int): DomainMemory
    {
        return repository.getMemory(memory_id)
    }
    suspend fun updateMemory(memory: DomainMemory)
    {
        return repository.updateMemory(memory)
    }
    suspend fun insertMemory(memory: DomainMemory)
    {
        return repository.insertMemory(memory)
    }
    suspend fun deleteMemory(memory: DomainMemory)
    {
        return repository.deleteMemory(memory)
    }
}