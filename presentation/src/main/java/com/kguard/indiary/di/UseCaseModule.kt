package com.kguard.indiary.di

import com.kguard.domain.repository.*
import com.kguard.domain.usecase.CharacterUseCase
import com.kguard.domain.usecase.MemoryUseCase
import com.kguard.domain.usecase.PersonUseCase
import com.kguard.domain.usecase.TagUseCase
import com.kguard.domain.usecase.WithUseCase
import com.kguard.indiary.usecase.*
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
    @Provides
    @Singleton
    fun provideCharacterUseCase(repository: CharacterRepository): CharacterUseCase {
        return CharacterUseCase(repository)
    }@Provides
    @Singleton
    fun provideTagUseCase(repository: TagRepository): TagUseCase {
        return TagUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideWithUseCase(repository: WithRepository): WithUseCase {
        return WithUseCase(repository)
    }
}