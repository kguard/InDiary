package com.kguard.indiary.core.database.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kguard.indiary.core.database.dao.MemoryDAO
import com.kguard.indiary.core.database.dao.PersonDAO
import com.kguard.indiary.core.database.entity.Memory
import com.kguard.indiary.core.database.entity.Person

@Database(
    entities = [Person::class, Memory::class],
    version = 6,
    exportSchema = true,
)
@TypeConverters(InDiaryTypeConverters::class)
abstract class InDiaryDatabase : RoomDatabase() {
    abstract val personDao: PersonDAO
    abstract val memoryDao: MemoryDAO
}