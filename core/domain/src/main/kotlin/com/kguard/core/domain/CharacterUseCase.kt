package com.kguard.core.domain

import com.kguard.core.domain.repository.CharacterRepository
import com.kguard.core.model.DomainCharacter


/**
 * todo character
 */
class CharacterUseCase(
    private val repository: CharacterRepository
) {
    suspend fun getCharacters(): List<DomainCharacter> {
        return repository.getCharacters()
    }

    suspend fun getCharacter(character_id: Int): DomainCharacter {
        return repository.getCharacter(character_id)
    }

    suspend fun getPersonCharacters(person_id: Int): List<DomainCharacter> {
        return repository.getPersonCharacters(person_id)
    }

    suspend fun updateCharacter(character: DomainCharacter) {
        return repository.updateCharacter(character)
    }

    suspend fun insertCharacter(character: DomainCharacter) {
        return repository.insertCharacter(character)
    }

    suspend fun deleteCharacter(character: DomainCharacter) {
        return repository.deleteCharacter(character)
    }
}