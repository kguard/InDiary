package com.kguard.indiary.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kguard.indiary.core.model.DomainMemory

@Entity(
    tableName = "Memory",
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["personId"],
            childColumns = ["personId"]
        )
    ]
)
data class Memory(
    @PrimaryKey(autoGenerate = true)
    val memoryId: Int = 0,
    @ColumnInfo(name = "MemoryTitle")
    var title: String,
    @ColumnInfo(name = "MemoryDate")
    var date: String,
    @ColumnInfo(name = "MemoryContent")
    var content: String?,
    @ColumnInfo(name = "MemoryImageList")
    var imageList: List<String?>,
    @ColumnInfo(name = "personId")
    var personId: Int? = null
)

fun Memory.toDomainMemory(): DomainMemory = DomainMemory(
    memoryId = memoryId,
    title = title,
    date = date,
    content = content,
    imageList = imageList,
    personId = personId
)