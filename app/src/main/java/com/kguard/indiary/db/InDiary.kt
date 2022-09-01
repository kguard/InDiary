package com.kguard.indiary.db

import android.graphics.Color
import androidx.room.*
import java.lang.reflect.Constructor
import java.time.LocalDate

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val person_id : Int=0,
    @ColumnInfo(name="PersonName")
    var name : String,
    @ColumnInfo(name="PersonBirth")
    var birth: String?,
    @ColumnInfo(name="PersonGender")
    var gender: Int,
    @ColumnInfo(name="PersonMemo")
    var memo: String?,
    @ColumnInfo(name="PersonMake")
    var make: String,
    @ColumnInfo(name="PersonFavorite")
    var favorite: Boolean,
    var Tag: List<Tag>?,
    var Character: List<Character>?,
    ){
    constructor():this(0,"",null,0,null,"",false,null,null)
}


@Entity
data class Memory(
    @PrimaryKey(autoGenerate = true) val memory_id : Int=0,
    @ColumnInfo(name="MemoryTitle")
    var title : String,
    @ColumnInfo(name="MemoryDate")
    var date : String,
    @ColumnInfo(name="MemoryContent")
    var content: String?,
    @ColumnInfo(name="MemoryImage1")
    var image1: String?,
    @ColumnInfo(name="MemoryImage2")
    var image2: String?,
    @ColumnInfo(name="MemoryImage3")
    var image3: String?,
    @ColumnInfo(name="MemoryImage4")
    var image4: String?,
    @ColumnInfo(name="MemoryImage5")
    var image5: String?,
    var With: List<With>?
){
    constructor():this(0,"","",null,null,null,null,null,null, null)
}

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true) val character_id : Int=0,
    @ColumnInfo(name="CharacterTitle")
    var title : String="",
    @ColumnInfo(name="CharacterContent")
    var content: String="",

    @ColumnInfo(name="CharacterImage1")
    var image1: String?=null,
    @ColumnInfo(name="CharacterImage2")
    var image2: String?=null,
    @ColumnInfo(name="CharacterImage3")
    var image3: String?=null,
    @ColumnInfo(name="CharacterImage4")
    var image4: String?=null,
    @ColumnInfo(name="CharacterImage5")
    var image5: String?=null
)

@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true) val tag_id : Int,
    @ColumnInfo(name="TagName")
    var name: String="",
    @ColumnInfo(name="TagColor")
    var color: String=""
)
@Entity
data class With(
    @PrimaryKey(autoGenerate = true) val with_id : Int,
    @ColumnInfo(name="WithName")
    var name: String="",
    @ColumnInfo(name="WithColor")
    var color: String=""
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




