package com.kguard.indiary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Person::class,Memory::class,Character::class,Tag::class,With::class], version = 1, exportSchema = false)
@TypeConverters(InDiaryTypeConverters::class)
abstract class InDiaryDB:RoomDatabase() {
    abstract fun personDao(): PersonDAO
    /*abstract fun personCharacterDao():PersonCharacterDAO
    abstract fun personTagDao():PersonTagDAO*/
    abstract fun memoryDao():MemoryDAO
    abstract fun characterDao():CharacterDAO
    abstract fun tagDao():TagDAO
    abstract fun withDao():WithDAO
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
                    ).addTypeConverter(InDiaryTypeConverters()).build()
                }
            }
            return instance
        }
    }
}