package com.kguard.indiary.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kguard.indiary.core.model.DomainPerson

@Entity(tableName = "Person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val personId: Int = 0,
    @ColumnInfo(name = "PersonName")
    var name: String,
    @ColumnInfo(name = "PersonBirth")
    var birth: String?,
    @ColumnInfo(name = "PersonGender")
    var gender: Int,
    @ColumnInfo(name = "PersonMemo")
    var memo: String?,
    @ColumnInfo(name = "PersonMake")
    var make: String,
    @ColumnInfo(name = "PersonFavorite")
    var favorite: Boolean
)

fun Person.toDomainPerson(): DomainPerson = DomainPerson(
    personId = personId,
    name = name,
    birth = birth,
    gender = gender,
    memo = memo,
    make = make,
    favorite = favorite
)