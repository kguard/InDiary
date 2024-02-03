package com.kguard.indiary.core.data.repositoryimpl

import com.kguard.indiary.core.database.dao.MemoryDAO
import com.kguard.indiary.core.database.entity.Memory
import com.kguard.indiary.core.database.entity.toDomainMemory
import com.kguard.indiary.core.data.repository.MemoryRepository
import com.kguard.indiary.core.model.DomainMemory
import javax.inject.Inject


class MemoryRepositoryImpl @Inject constructor(
    private val dao: MemoryDAO
) : MemoryRepository {
    override suspend fun getMemories(): List<DomainMemory> {
        return dao.getMemories().map { it.toDomainMemory() }
    }

    override suspend fun getPersonMemories(personId: Int): List<DomainMemory> {
        return dao.getPersonMemories(personId).map { it.toDomainMemory() }
    }

    override suspend fun getMemory(memoryId: Int): DomainMemory {
        return dao.getMemory(memoryId).toDomainMemory()
    }

    override suspend fun updateMemory(memory: DomainMemory) {
        val memoryEntity = Memory(
            memory.memoryId,
            memory.title,
            memory.date,
            memory.content,
            memory.imageList,
            memory.personId
        )
        dao.updateMemory(memoryEntity)
    }

    override suspend fun insertMemory(memory: DomainMemory) {
        val memoryEntity = Memory(
            memory.memoryId,
            memory.title,
            memory.date,
            memory.content,
            memory.imageList,
            memory.personId
        )
        dao.insertMemory(memoryEntity)
    }

    override suspend fun deleteMemory(memory: DomainMemory) {
        val memoryEntity = Memory(
            memory.memoryId,
            memory.title,
            memory.date,
            memory.content,
            memory.imageList,
            memory.personId
        )
        dao.deleteMemory(memoryEntity)
    }
}