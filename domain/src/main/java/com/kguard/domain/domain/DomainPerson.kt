package com.kguard.domain.domain

data class DomainPerson (
    val person_id :Int =0,
    var name :String ="",
    var birth:String?=null,
    var gender:Int=0,
    var memo: String?=null,
    var make: String ="",
    var favorite: Boolean=false
        )