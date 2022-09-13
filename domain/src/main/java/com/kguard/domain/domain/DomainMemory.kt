package com.kguard.domain.domain

data class DomainMemory(
    val memory_id:Int =0,
    var title:String="",
    var date:String="",
    var content:String?=null,
    var imageList: ArrayList<String?> = arrayListOf(),
    var person_id:Int? = null
        )
