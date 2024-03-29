package com.kguard.indiary.core.model

data class DomainMemory(
    val memoryId: Int = 0,
    var title: String = "",
    var date: String = "",
    var content: String? = null,
    var imageList: List<String?> = arrayListOf(),
    var personId: Int? = null
)

class InvalidMemoryException(message: String) : Exception(message)
