package com.kguard.indiary.usecase

import com.kguard.domain.domain.DomainTag
import com.kguard.domain.repository.TagRepository
import javax.inject.Inject

/**
 * todo tag
 */
class TagUseCase @Inject constructor(
    private val repository: TagRepository
) {
    suspend fun getTags(): List<DomainTag>
    {
        return repository.getTags()
    }
    suspend fun getTag(tag_id:Int): DomainTag
    {
        return repository.getTag(tag_id)
    }
    suspend fun getPersonTags(person_id:Int): List<DomainTag>
    {
        return repository.getPersonTags(person_id)
    }
    suspend fun updateTag(tag: DomainTag)
    {
        return repository.updateTag(tag)
    }
    suspend fun insertTag(tag: DomainTag)
    {
        return repository.insertTag(tag)
    }
    suspend fun deleteTag(tag: DomainTag)
    {
        return repository.deleteTag(tag)
    }
}