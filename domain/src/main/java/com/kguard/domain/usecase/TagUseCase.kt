package com.kguard.domain.usecase

import com.kguard.domain.model.DomainTag
import com.kguard.domain.repository.TagRepository
/**
 * todo tag
 */
class TagUseCase (
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