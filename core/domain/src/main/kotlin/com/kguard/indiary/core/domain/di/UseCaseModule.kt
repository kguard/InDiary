package com.kguard.indiary.core.domain.di

import com.kguard.indiary.core.domain.MemoryUseCase
import com.kguard.indiary.core.domain.PersonUseCase
import com.kguard.indiary.core.data.repository.MemoryRepository
import com.kguard.indiary.core.data.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun providePersonUseCase(repository: PersonRepository): PersonUseCase {
        return PersonUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMemoryUseCase(repository: MemoryRepository): MemoryUseCase {
        return MemoryUseCase(repository)
    }
}