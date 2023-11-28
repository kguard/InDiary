package com.kguard.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kguard.core.data.local.dao.MemoryDAO
import com.kguard.core.data.local.dao.PersonDAO
import com.kguard.core.data.local.dao.*
import com.kguard.core.data.local.entity.Memory
import com.kguard.core.data.local.entity.Person
import com.kguard.core.data.local.entity.Character
import com.kguard.core.data.local.entity.Tag
import com.kguard.core.data.local.entity.With

@Database(
    entities = [Person::class, Memory::class, Character::class, Tag::class, With::class],
    version = 5,
    exportSchema = false
)
abstract class InDiaryDatabase : RoomDatabase() {
    abstract val personDao: PersonDAO
    abstract val memoryDao: MemoryDAO
    abstract val characterDao: CharacterDAO
    abstract val tagDao: TagDAO
    abstract val withDao: WithDAO
}