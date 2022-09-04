package com.kguard.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kguard.domain.domain.DomainPerson

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
)
fun Person.toDomainPerson() : DomainPerson = DomainPerson(
    person_id, name, birth, gender, memo, make, favorite
)