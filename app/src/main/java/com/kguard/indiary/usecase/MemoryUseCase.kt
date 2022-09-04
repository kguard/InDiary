package com.kguard.indiary.usecase

import com.kguard.domain.domain.DomainMemory
import com.kguard.domain.repository.MemoryRepository
import javax.inject.Inject

class MemoryUseCase @Inject constructor(
    private val repository: MemoryRepository
) {
    suspend fun getMemories():List<DomainMemory>
    {
        return repository.getMemories()
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
        return repository.insetMemory(memory)
    }
    suspend fun deleteMemory(memory: DomainMemory)
    {
        return repository.deleteMemory(memory)
    }
}