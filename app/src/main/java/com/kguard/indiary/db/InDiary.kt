package com.kguard.indiary.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val person_id : Int=0,
    val name : String,
    val birth: String,
    val gender: Int,
    val memo: String,
    val make: LocalDate,
    val favorite: Boolean,
    val color: String
    )

@Entity
data class Memory(
    @PrimaryKey(autoGenerate = true) val memory_id : Int=0,
    val title : String,
    val date : String,
    val contents: String
)

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true) val character_id : Int,
    val title : String,
    val contents: String
)

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true) val tag_id : Int,
    val name: String,
    val color: String
)

@Entity
data class Image(
    @PrimaryKey(autoGenerate = true) val image_id: Int,
    val file : String,
)

@Entity(foreignKeys = [
    ForeignKey(
        entity = Person::class,
        parentColumns = ["person_id"],
        childColumns = ["person_id"]
    ),
    ForeignKey(
        entity = Character::class,
        parentColumns = ["character_id"],
        childColumns = ["character_id"]
    )
])
data class PersonCharacter(
    @PrimaryKey(autoGenerate = true) val person_character_id: Int,
)

@Entity(foreignKeys = [
    ForeignKey(
        entity = Person::class,
        parentColumns = ["person_id"],
        childColumns = ["person_id"]
    ),
    ForeignKey(
        entity = Tag::class,
        parentColumns = ["tag_id"],
        childColumns = ["tag_id"]
    )
])
data class PersonTag(
    @PrimaryKey(autoGenerate = true) val person_tag_id: Int
)

@Entity(foreignKeys = [
    ForeignKey(
        entity = Person::class,
        parentColumns = ["person_id"],
        childColumns = ["person_id"]
    ),
    ForeignKey(
        entity = Memory::class,
        parentColumns = ["memory_id"],
        childColumns = ["memory_id"]
    )
])
data class PersonMemory(
    @PrimaryKey(autoGenerate = true) val person_memory_id: Int
)

@Entity(foreignKeys = [
    ForeignKey(
        entity = Image::class,
        parentColumns = ["image_id"],
        childColumns = ["image_id"]
    ),
    ForeignKey(
        entity = Character::class,
        parentColumns = ["character_id"],
        childColumns = ["character_id"]
    )
])
data class ImageCharacter(
    @PrimaryKey(autoGenerate = true) val image_character_id: Int
)

@Entity(foreignKeys = [
    ForeignKey(
        entity = Image::class,
        parentColumns = ["image_id"],
        childColumns = ["image_id"]
    ),
    ForeignKey(
        entity = Memory::class,
        parentColumns = ["memory_id"],
        childColumns = ["memory_id"]
    )
])
data class ImageMemory(
    @PrimaryKey(autoGenerate = true) val image_memory_id: Int
)

