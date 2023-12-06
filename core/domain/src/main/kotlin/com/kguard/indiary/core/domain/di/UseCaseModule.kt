package com.kguard.indiary.core.domain.di

import com.kguard.indiary.core.domain.CharacterUseCase
import com.kguard.indiary.core.domain.MemoryUseCase
import com.kguard.indiary.core.domain.PersonUseCase
import com.kguard.indiary.core.domain.TagUseCase
import com.kguard.indiary.core.domain.WithUseCase
import com.kguard.indiary.core.data.repository.CharacterRepository
import com.kguard.indiary.core.data.repository.MemoryRepository
import com.kguard.indiary.core.data.repository.PersonRepository
import com.kguard.indiary.core.data.repository.TagRepository
import com.kguard.indiary.core.data.repository.WithRepository
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
    }

    @Provides
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