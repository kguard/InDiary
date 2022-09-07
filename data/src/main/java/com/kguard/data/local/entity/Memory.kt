package com.kguard.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kguard.domain.domain.DomainMemory

@Entity(tableName = "Memory")
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
    @ColumnInfo(name="With")
    var with: String?
)
fun Memory.toDomainMemory(): DomainMemory = DomainMemory(
    memory_id, title, date, content, image1, image2, image3, image4, image5, with
)