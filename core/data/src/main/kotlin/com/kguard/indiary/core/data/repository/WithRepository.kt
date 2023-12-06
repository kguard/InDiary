package com.kguard.indiary.core.data.repository

import com.kguard.indiary.core.model.DomainWith

interface WithRepository {
    suspend fun getWiths(): List<DomainWith>
    suspend fun getWith(with_id: Int): DomainWith
    suspend fun getMemoryWiths(memory_id: Int): List<DomainWith>
    suspend fun updateWith(with: DomainWith)
    suspend fun insertWith(with: DomainWith)
    suspend fun deleteWith(with: DomainWith)
}