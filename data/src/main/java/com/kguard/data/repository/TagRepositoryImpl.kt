package com.kguard.data.repository

import com.kguard.data.local.dao.TagDAO
import com.kguard.data.local.entity.Tag
import com.kguard.data.local.entity.toDomainTag
import com.kguard.domain.domain.DomainTag
import com.kguard.domain.repository.TagRepository
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val dao: TagDAO
) : TagRepository
{
    override suspend fun getTags(): List<DomainTag> {
        return dao.getTags().map { it.toDomainTag() }
    }

    override suspend fun getTag(tag_id: Int): DomainTag {
        return dao.getTag(tag_id).toDomainTag()
    }

    override suspend fun getPersonTags(person_id: Int): List<DomainTag> {
        return dao.getPersonTags(person_id).map { it.toDomainTag() }
    }

    override suspend fun updateTag(tag: DomainTag) {
        val tagEntity = Tag(tag.tag_id,tag.name,tag.color,tag.person_id)
        dao.updateTag(tagEntity)
    }

    override suspend fun insertTag(tag: DomainTag) {
        val tagEntity = Tag(tag.tag_id,tag.name,tag.color,tag.person_id)
        dao.insertTag(tagEntity)
    }

    override suspend fun deleteTag(tag: DomainTag) {
        val tagEntity = Tag(tag.tag_id,tag.name,tag.color,tag.person_id)
        dao.deleteTag(tagEntity)
    }

}