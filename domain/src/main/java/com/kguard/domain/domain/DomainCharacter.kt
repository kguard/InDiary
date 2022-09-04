package com.kguard.domain.domain

data class DomainCharacter(
    val character_id : Int,
    var title : String,
    var content: String,
    val person_id:Int,
    var image1: String?,
    var image2: String?,
    var image3: String?,
    var image4: String?,
    var image5: String?
)
