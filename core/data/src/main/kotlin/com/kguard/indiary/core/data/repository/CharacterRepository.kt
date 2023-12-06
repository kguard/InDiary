package com.kguard.indiary.core.data.repository

import com.kguard.indiary.core.model.DomainCharacter

interface CharacterRepository {
    suspend fun getCharacters(): List<DomainCharacter>
    suspend fun getCharacter(character_id: Int): DomainCharacter
    suspend fun getPersonCharacters(person_id: Int): List<DomainCharacter>
    suspend fun updateCharacter(character: DomainCharacter)
    suspend fun insertCharacter(character: DomainCharacter)
    suspend fun deleteCharacter(character: DomainCharacter)
}