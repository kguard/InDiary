package com.kguard.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kguard.core.domain.model.DomainMemory

@Entity(tableName = "Memory",
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["person_id"],
            childColumns = ["person_id"]
        )
    ])
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
    @ColumnInfo(name="person_id")
    var person_id:Int?
)
fun Memory.toDomainMemory(): DomainMemory = DomainMemory(
    memory_id, title, date, content, arrayListOf(image1,image2,image3,image4,image5), person_id
)