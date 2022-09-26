package com.kguard.data.di

import android.app.Application
import androidx.room.Room
import com.kguard.data.local.database.InDiaryDatabase
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
    fun provideInDiaryDatabase(app:Application):InDiaryDatabase{
        return Room.databaseBuilder(
            app,InDiaryDatabase::class.java,"InDiary DB"
        ).fallbackToDestructiveMigration().build()
    }
}