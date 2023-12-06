package com.kguard.indiary.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kguard.indiary.core.database.dao.MemoryDAO
import com.kguard.indiary.core.database.dao.PersonDAO
import com.kguard.indiary.core.database.entity.Memory
import com.kguard.indiary.core.database.entity.Person
import com.kguard.indiary.core.database.entity.Character
import com.kguard.indiary.core.database.entity.Tag
import com.kguard.indiary.core.database.entity.With
import com.kguard.indiary.core.database.dao.CharacterDAO
import com.kguard.indiary.core.database.dao.TagDAO
import com.kguard.indiary.core.database.dao.WithDAO

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