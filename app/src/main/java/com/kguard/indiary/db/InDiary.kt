package com.kguard.indiary.db

import android.graphics.Color
import androidx.room.*
import java.lang.reflect.Constructor
import java.time.LocalDate

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val person_id : Int=0,
    @ColumnInfo(name="PersonName")
    val name : String,
    @ColumnInfo(name="PersonBirth")
    val birth: String?,
    @ColumnInfo(name="PersonGender")
    val gender: Int,
    @ColumnInfo(name="PersonMemo")
    val memo: String?,
    @ColumnInfo(name="PersonMake")
    val make: String,
    @ColumnInfo(name="PersonFavorite")
    var favorite: Boolean,
    val Tag: List<Tag>?,
    val Character: List<Character>?,
    )/*{
    constructor():this(0,"",null,0,null,"",false,null,null)
}*/


@Entity
data class Memory(
    @PrimaryKey(autoGenerate = true) val memory_id : Int=0,
    @ColumnInfo(name="MemoryTitle")
    val title : String,
    @ColumnInfo(name="MemoryDate")
    val date : String,
    @ColumnInfo(name="MemoryContent")
    val content: String?,
    @ColumnInfo(name="MemoryImage1")
    val image1: String?,
    @ColumnInfo(name="MemoryImage2")
    val image2: String?,
    @ColumnInfo(name="MemoryImage3")
    val image3: String?,
    @ColumnInfo(name="MemoryImage4")
    val image4: String?,
    @ColumnInfo(name="MemoryImage5")
    val image5: String?,
    val With: List<With>?
)/*{
    constructor():this(0,"","",null,null,null,null,null,null, null)
}*/

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true) val character_id : Int=0,
    @ColumnInfo(name="CharacterTitle")
    val title : String="",
    @ColumnInfo(name="CharacterContent")
    val content: String="",

    @ColumnInfo(name="CharacterImage1")
    val image1: String?=null,
    @ColumnInfo(name="CharacterImage2")
    val image2: String?=null,
    @ColumnInfo(name="CharacterImage3")
    val image3: String?=null,
    @ColumnInfo(name="CharacterImage4")
    val image4: String?=null,
    @ColumnInfo(name="CharacterImage5")
    val image5: String?=null
)

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true) val tag_id : Int,
    @ColumnInfo(name="TagName")
    val name: String="",
    @ColumnInfo(name="TagColor")
    val color: String=""
)
@Entity
data class With(
    @PrimaryKey(autoGenerate = true) val with_id : Int,
    @ColumnInfo(name="WithName")
    val name: String="",
    @ColumnInfo(name="WithColor")
    val color: String=""
)


/*data class MemoryWith(
    @Embedded val memory: Memory,
    @Relation(parentColumn = "memory_id", entityColumn = "memory_with_id")
    val with: List<With>?,
)
data class PersonCharacter(
    @Embedded val person: Person,
    @Relation(
        parentColumn = "person_id",
        entityColumn = "character_id"
    )
    val characters: List<Character>?
)

data class PersonTag(

    @Embedded val person: Person,
    @Relation(
        parentColumn = "person_id",
        entityColumn = "tag_id"
    )
    val Tag: List<Tag>?
)*/




