package com.kguard.data.repository

import com.kguard.data.local.dao.MemoryDAO
import com.kguard.data.local.entity.Memory
import com.kguard.data.local.entity.toDomainMemory
import com.kguard.domain.domain.DomainMemory
import com.kguard.domain.repository.MemoryRepository
import javax.inject.Inject

class MemoryRepositoryImpl @Inject constructor(
    private val dao : MemoryDAO
):MemoryRepository {
    override suspend fun getMemories(): List<DomainMemory> {
        return dao.getMemories().map { it.toDomainMemory() }
    }

    override suspend fun getMemory(memory_id: Int): DomainMemory {
        return dao.getMemory(memory_id).toDomainMemory()
    }

    override suspend fun updateMemory(memory: DomainMemory) {
        val memoryEntity = Memory(memory.memory_id,memory.title,memory.date,memory.content,memory.image1,memory.image2,memory.image3,memory.image4,memory.image5)
        dao.updateMemory(memoryEntity)
    }

    override suspend fun insetMemory(memory: DomainMemory) {
        val memoryEntity = Memory(memory.memory_id,memory.title,memory.date,memory.content,memory.image1,memory.image2,memory.image3,memory.image4,memory.image5)
        dao.insertMemory(memoryEntity)
    }

    override suspend fun deleteMemory(memory: DomainMemory) {
        val memoryEntity = Memory(memory.memory_id,memory.title,memory.date,memory.content,memory.image1,memory.image2,memory.image3,memory.image4,memory.image5)
        dao.deleteMemory(memoryEntity)
    }
}