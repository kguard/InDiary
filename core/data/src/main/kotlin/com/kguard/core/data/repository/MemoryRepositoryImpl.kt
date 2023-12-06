package com.kguard.core.data.repository

import com.kguard.core.database.dao.MemoryDAO
import com.kguard.core.database.entity.Memory
import com.kguard.core.database.entity.toDomainMemory
import com.kguard.core.domain.repository.MemoryRepository
import com.kguard.core.model.DomainMemory
import javax.inject.Inject


class MemoryRepositoryImpl @Inject constructor(
    private val dao: MemoryDAO
) : MemoryRepository {
    override suspend fun getMemories(): List<DomainMemory> {
        return dao.getMemories().map { it.toDomainMemory() }
    }

    override suspend fun getPersonMemories(person_id: Int): List<DomainMemory> {
        return dao.getPersonMemories(person_id).map { it.toDomainMemory() }
    }

    override suspend fun getMemory(memory_id: Int): DomainMemory {
        return dao.getMemory(memory_id).toDomainMemory()
    }

    override suspend fun updateMemory(memory: DomainMemory) {
        val memoryEntity = Memory(
            memory.memory_id, memory.title, memory.date, memory.content,
            memory.imageList[0],
            memory.imageList[1],
            memory.imageList[2],
            memory.imageList[3],
            memory.imageList[4], memory.person_id
        )
        dao.updateMemory(memoryEntity)
    }

    override suspend fun insertMemory(memory: DomainMemory) {
        val memoryEntity = Memory(
            memory.memory_id, memory.title, memory.date, memory.content,
            memory.imageList[0],
            memory.imageList[1],
            memory.imageList[2],
            memory.imageList[3],
            memory.imageList[4], memory.person_id
        )
        dao.insertMemory(memoryEntity)
    }

    override suspend fun deleteMemory(memory: DomainMemory) {
        val memoryEntity = Memory(
            memory.memory_id, memory.title, memory.date, memory.content,
            memory.imageList[0],
            memory.imageList[1],
            memory.imageList[2],
            memory.imageList[3],
            memory.imageList[4], memory.person_id
        )
        dao.deleteMemory(memoryEntity)
    }
}