package com.kguard.indiary.db

import androidx.room.*
import java.time.LocalDate

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val person_id : Int=0,
    val name : String,
    val birth: String?,
    val gender: Int,
    val memo: String?,

    val make: String,
    var favorite: Boolean,
    val color: String

    )

@Entity
data class Memory(
    @PrimaryKey(autoGenerate = true) val memory_id : Int=0,
    val title : String,
    val date : String,
    val contents: String?,

    val image1: String?,
    val image2: String?,
    val image3: String?,
    val image4: String?,
    val image5: String?
)

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true) val character_id : Int,
    val title : String,
    val contents: String,

    val image1: String?,
    val image2: String?,
    val image3: String?,
    val image4: String?,
    val image5: String?
)

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true) val tag_id : Int,
    val name: String,
    val color: String
)
@Entity
data class With(
    @PrimaryKey(autoGenerate = true) val with_id : Int,
    val name: String,
    val color: String
)


data class MemoryWith(
    @Embedded val memory: Memory,
    @Relation(
        parentColumn = "memory_id",
        entityColumn = "memory_with_id"
    )
    val with: List<With>,
)

data class PersonCharacter(
    @Embedded val person: Person,
    @Relation(
        parentColumn = "person_id",
        entityColumn = "character_id"
    )
    val characters: List<Character>
)


data class PersonTag(
    @Embedded val person: Person,
    @Relation(
        parentColumn = "person_id",
        entityColumn = "tag_id"
    )
    val tags: List<Tag>
)




