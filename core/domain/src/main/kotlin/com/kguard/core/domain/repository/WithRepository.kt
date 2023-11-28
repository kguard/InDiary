package com.kguard.core.domain.repository

import com.kguard.core.domain.model.DomainWith

interface WithRepository {
    suspend fun getWiths(): List<DomainWith>
    suspend fun getWith(with_id:Int): DomainWith
    suspend fun getMemoryWiths(memory_id:Int) :List<DomainWith>
    suspend fun updateWith(with: DomainWith)
    suspend fun insertWith(with: DomainWith)
    suspend fun deleteWith(with: DomainWith)
}