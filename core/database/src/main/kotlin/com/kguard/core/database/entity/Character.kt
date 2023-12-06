package com.kguard.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kguard.core.model.DomainCharacter

@Entity(
    tableName = "Character",
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["person_id"],
            childColumns = ["person_id"]
        )
    ]
)
data class Character(
    @PrimaryKey(autoGenerate = true) val character_id: Int = 0,
    @ColumnInfo(name = "CharacterTitle")
    var title: String,
    @ColumnInfo(name = "CharacterContent")
    var content: String,
    @ColumnInfo(name = "person_id")
    val person_id: Int
)

fun Character.toDomainCharacter(): DomainCharacter = DomainCharacter(
    character_id, title, content, person_id
)
