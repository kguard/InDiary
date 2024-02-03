package com.kguard.indiary.core.model

data class DomainPerson(
    val personId: Int = 0,
    var name: String = "",
    var birth: String? = null,
    var gender: Int = -1,
    var memo: String? = null,
    var make: String = "",
    var favorite: Boolean = false
)

class InvalidPersonException(message: String) : Exception(message)