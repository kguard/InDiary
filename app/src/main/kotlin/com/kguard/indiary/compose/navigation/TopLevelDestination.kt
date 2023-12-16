package com.kguard.indiary.compose.navigation

import com.kguard.indiary.feature.person.R as personR
import com.kguard.indiary.feature.memory.R as memoryR

enum class TopLevelDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    PERSON(
        selectedIcon = personR.drawable.ic_person_2_fill,
        unselectedIcon = personR.drawable.ic_person_2_line,
        iconTextId = personR.string.Person,
        titleTextId = personR.string.Person
    ),
    MEMORY(
        selectedIcon = memoryR.drawable.ic_memory_2_fill,
        unselectedIcon = memoryR.drawable.ic_memory_2_line,
        iconTextId = memoryR.string.Memory,
        titleTextId = memoryR.string.Memory
    )
}

