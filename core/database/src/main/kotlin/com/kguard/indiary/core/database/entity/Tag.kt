package com.kguard.indiary.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kguard.indiary.core.model.DomainTag

@Entity(
    tableName = "Tag",
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["person_id"],
            childColumns = ["person_id"]
        )
    ]
)
data class Tag(
    @PrimaryKey(autoGenerate = true) val tag_id: Int,
    @ColumnInfo(name = "TagName")
    val name: String,
    @ColumnInfo(name = "TagColor")
    val color: String,
    @ColumnInfo(name = "person_id")
    val person_id: Int

)

fun Tag.toDomainTag(): DomainTag = DomainTag(
    tag_id, name, color, person_id
)