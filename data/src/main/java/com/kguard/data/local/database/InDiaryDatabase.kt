package com.kguard.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kguard.data.local.dao.*
import com.kguard.data.local.entity.Memory
import com.kguard.data.local.entity.Person
import com.kguard.data.local.entity.Character
import com.kguard.data.local.entity.Tag
import com.kguard.data.local.entity.With

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