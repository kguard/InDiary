package com.kguard.core.domain.repository

import com.kguard.core.domain.model.DomainTag

interface TagRepository {
    suspend fun getTags(): List<DomainTag>
    suspend fun getTag(tag_id:Int): DomainTag
    suspend fun getPersonTags(person_id:Int): List<DomainTag>
    suspend fun updateTag(tag: DomainTag)
    suspend fun insertTag(tag: DomainTag)
    suspend fun deleteTag(tag: DomainTag)
}