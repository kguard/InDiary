package com.kguard.indiary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class,Memory::class,Character::class,Tag::class,With::class], version = 1, exportSchema = false)
abstract class InDiaryDB:RoomDatabase() {
    abstract fun personTagDao(): PersonTagDAO
    abstract fun personCharacterDao():PersonCharacterDAO
    abstract fun memoryWithDao():MemoryDAO
    companion object{
        private var instance: InDiaryDB? =null
        @Synchronized
        fun getInstance(context: Context):InDiaryDB?{
            if( instance==null){
                synchronized(InDiaryDB::class){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        InDiaryDB::class.java,
                        "InDiary-db"
                    ).build()
                }
            }
            return instance
        }
    }
}