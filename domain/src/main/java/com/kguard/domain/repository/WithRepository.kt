package com.kguard.domain.repository

import com.kguard.domain.domain.DomainWith

interface WithRepository {
    suspend fun getWiths(): List<DomainWith>
    suspend fun getWith(with_id:Int): DomainWith
    suspend fun getMemoryWiths(memory_id:Int) :List<DomainWith>
    suspend fun updateWith(with: DomainWith)
    suspend fun insertWith(with: DomainWith)
    suspend fun deleteWith(with: DomainWith)
}