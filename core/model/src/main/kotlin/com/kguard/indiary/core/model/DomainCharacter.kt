package com.kguard.indiary.core.model

data class DomainCharacter(
    val character_id: Int,
    var title: String,
    var content: String,
    val person_id: Int
)