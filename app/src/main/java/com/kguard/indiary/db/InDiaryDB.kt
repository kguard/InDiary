package com.kguard.indiary.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Person::class,Memory::class,Character::class,Tag::class,With::class], version = 1, exportSchema = false)
abstract class InDiaryDB:RoomDatabase() {
    abstract fun personTagDao(): PersonTagDAO
    abstract fun personCharacterDao():PersonCharacterDAO
    abstract fun memoryWithDao():MemoryDAO
}