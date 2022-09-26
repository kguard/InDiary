package com.kguard.indiary.usecase

import com.kguard.domain.domain.DomainWith
import com.kguard.domain.repository.WithRepository
import javax.inject.Inject

/**
 * todo with
 */
class WithUseCase @Inject constructor(
    private val repository: WithRepository
) {
    suspend fun getWiths(): List<DomainWith>
    {
        return repository.getWiths()
    }
    suspend fun getWith(with_id:Int): DomainWith
    {
        return repository.getWith(with_id)
    }
    suspend fun getMemoryWiths(memory_id:Int) :List<DomainWith>
    {
        return repository.getMemoryWiths(memory_id)
    }
    suspend fun updateWith(with: DomainWith)
    {
        return repository.updateWith(with)
    }
    suspend fun insertWith(with: DomainWith)
    {
        return repository.insertWith(with)
    }
    suspend fun deleteWith(with: DomainWith)
    {
        return repository.deleteWith(with)
    }
}