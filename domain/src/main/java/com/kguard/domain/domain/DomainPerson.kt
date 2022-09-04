package com.kguard.domain.domain

data class DomainPerson (
    val person_id :Int,
    var name :String,
    var birth:String?,
    var gender:Int,
    var memo: String?,
    val make: String,
    var favorite: Boolean
        )