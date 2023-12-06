package com.kguard.indiary.core.data.repositoryimpl


import com.kguard.indiary.core.database.dao.WithDAO
import com.kguard.indiary.core.database.entity.With
import com.kguard.indiary.core.database.entity.toDomainWith
import com.kguard.indiary.core.data.repository.WithRepository
import com.kguard.indiary.core.model.DomainWith
import javax.inject.Inject

class WithRepositoryImpl @Inject constructor(
    private val dao: WithDAO
) : WithRepository {
    override suspend fun getWiths(): List<DomainWith> {
        return dao.getWiths().map { it.toDomainWith() }
    }

    override suspend fun getWith(with_id: Int): DomainWith {
        return dao.getWith(with_id).toDomainWith()
    }

    override suspend fun getMemoryWiths(memory_id: Int): List<DomainWith> {
        return dao.getMemoryWiths(memory_id).map { it.toDomainWith() }
    }

    override suspend fun updateWith(with: DomainWith) {
        val withEntity = With(with.with_id, with.name, with.color, with.memory_id)
        dao.updateWith(withEntity)
    }

    override suspend fun insertWith(with: DomainWith) {
        val withEntity = With(with.with_id, with.name, with.color, with.memory_id)
        dao.insertWith(withEntity)
    }

    override suspend fun deleteWith(with: DomainWith) {
        val withEntity = With(with.with_id, with.name, with.color, with.memory_id)
        dao.deleteWith(withEntity)
    }
}