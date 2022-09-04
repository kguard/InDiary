package com.kguard.data.di

import com.kguard.data.local.database.InDiaryDatabase
import com.kguard.data.repository.*
import com.kguard.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePersonRepository(db:InDiaryDatabase):PersonRepository{
        return PersonRepositoryImpl(db.personDao)
    }
    @Provides
    @Singleton
    fun provideMemoryRepository(db:InDiaryDatabase):MemoryRepository{
        return MemoryRepositoryImpl(db.memoryDao)
    }
    @Provides
    @Singleton
    fun provideCharacterRepository(db:InDiaryDatabase):CharacterRepository{
        return CharacterRepositoryImpl(db.characterDao)
    }
    @Provides
    @Singleton
    fun provideTagRepository(db:InDiaryDatabase):TagRepository{
        return TagRepositoryImpl(db.tagDao)
    }
    @Provides
    @Singleton
    fun provideWithRepository(db:InDiaryDatabase):WithRepository{
        return WithRepositoryImpl(db.withDao)
    }
}