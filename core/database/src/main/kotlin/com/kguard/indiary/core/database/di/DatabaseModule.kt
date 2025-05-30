package com.kguard.indiary.core.database.di

import android.app.Application
import androidx.room.Room
import com.kguard.indiary.core.database.database.InDiaryDatabase
import com.kguard.indiary.core.database.database.InDiaryTypeConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideInDiaryDatabase(app: Application): InDiaryDatabase {
        return Room.databaseBuilder(
                app, InDiaryDatabase::class.java, "InDiary DB"
            ).addTypeConverter(InDiaryTypeConverters()).fallbackToDestructiveMigration(false).build()
    }
}