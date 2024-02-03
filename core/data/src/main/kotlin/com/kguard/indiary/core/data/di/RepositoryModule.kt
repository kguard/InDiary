package com.kguard.indiary.core.data.di

import com.kguard.indiary.core.data.repositoryimpl.MemoryRepositoryImpl
import com.kguard.indiary.core.data.repositoryimpl.PersonRepositoryImpl
import com.kguard.indiary.core.database.database.InDiaryDatabase
import com.kguard.indiary.core.data.repository.MemoryRepository
import com.kguard.indiary.core.data.repository.PersonRepository
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
    fun providePersonRepository(db: InDiaryDatabase): PersonRepository {
        return PersonRepositoryImpl(db.personDao)
    }

    @Provides
    @Singleton
    fun provideMemoryRepository(db: InDiaryDatabase): MemoryRepository {
        return MemoryRepositoryImpl(db.memoryDao)
    }

}