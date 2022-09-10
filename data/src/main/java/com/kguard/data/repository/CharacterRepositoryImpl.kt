package com.kguard.data.repository

import com.kguard.data.local.dao.CharacterDAO
import com.kguard.data.local.entity.Character
import com.kguard.data.local.entity.toDomainCharacter
import com.kguard.domain.domain.DomainCharacter
import com.kguard.domain.repository.CharacterRepository
import javax.inject.Inject

class  CharacterRepositoryImpl @Inject constructor(
    private val dao :CharacterDAO
): CharacterRepository
{
    override suspend fun getCharacters(): List<DomainCharacter> {
        return dao.getCharacters().map {it.toDomainCharacter()}
    }

    override suspend fun getCharacter(character_id: Int): DomainCharacter {
        return dao.getCharacter(character_id).toDomainCharacter()
    }

    override suspend fun getPersonCharacters(person_id: Int): List<DomainCharacter> {
        return dao.getPersonCharacters(person_id).map { it.toDomainCharacter() }
    }

    override suspend fun updateCharacter(character: DomainCharacter) {
        val characterEntity=Character(character.character_id,character.title,character.content,character.person_id)
        dao.updateCharacter(characterEntity)
    }

    override suspend fun insertCharacter(character: DomainCharacter) {
        val characterEntity=Character(character.character_id,character.title,character.content,character.person_id)
        dao.insertCharacter(characterEntity)
    }

    override suspend fun deleteCharacter(character: DomainCharacter) {
        val characterEntity=Character(character.character_id,character.title,character.content,character.person_id)
        dao.deleteCharacter(characterEntity)
    }
}