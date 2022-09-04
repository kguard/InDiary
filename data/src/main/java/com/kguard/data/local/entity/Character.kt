package com.kguard.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kguard.domain.domain.DomainCharacter

@Entity(
    foreignKeys = [
    ForeignKey(
        entity = Person::class,
        parentColumns = ["person_id"],
        childColumns = ["person_id"]
    )
])
data class Character(
    @PrimaryKey(autoGenerate = true) val character_id : Int=0,
    @ColumnInfo(name="CharacterTitle")
    var title : String,
    @ColumnInfo(name="CharacterContent")
    var content: String,
    @ColumnInfo(name="PersonId")
    val person_id:Int,
    @ColumnInfo(name="CharacterImage1")
    var image1: String?,
    @ColumnInfo(name="CharacterImage2")
    var image2: String?,
    @ColumnInfo(name="CharacterImage3")
    var image3: String?,
    @ColumnInfo(name="CharacterImage4")
    var image4: String?,
    @ColumnInfo(name="CharacterImage5")
    var image5: String?
)
fun Character.toDomainCharacter(): DomainCharacter = DomainCharacter(
    character_id, title, content, person_id, image1, image2, image3, image4, image5
)
