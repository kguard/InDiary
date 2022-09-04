package com.kguard.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.kguard.domain.domain.DomainWith

@Entity(
    foreignKeys =[
        ForeignKey(
            entity = Memory::class,
            parentColumns = ["memory_id"],
            childColumns = ["memory_id"]
        )
    ]
)
data class With(
    @PrimaryKey(autoGenerate = true) val with_id : Int,
    @ColumnInfo(name="WithName")
    var name: String,
    @ColumnInfo(name="WithColor")
    var color: String,
    @ColumnInfo(name="MemoryId")
    val memory_id: Int
)
fun With.toDomainWith():DomainWith= DomainWith(
    with_id, name, color, memory_id
)