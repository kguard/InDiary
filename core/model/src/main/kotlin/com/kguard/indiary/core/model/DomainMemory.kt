package com.kguard.indiary.core.model

data class DomainMemory(
    val memory_id: Int = 0,
    var title: String = "",
    var date: String = "",
    var content: String? = null,
    var imageList: ArrayList<String?> = arrayListOf(),
    var person_id: Int? = null
)

class InvalidMemoryException(message: String) : Exception(message)
