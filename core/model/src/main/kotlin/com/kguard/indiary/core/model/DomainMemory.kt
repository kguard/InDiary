package com.kguard.indiary.core.model

data class DomainMemory(
    val memory_id: Int = -1,
    var title: String = "",
    var date: String = "",
    var content: String? = null,
    var imageList: List<String?> = arrayListOf(),
    var person_id: Int? = null
)

class InvalidMemoryException(message: String) : Exception(message)
