package com.ymorenoanz.seren.di

import com.ymorenoanz.seren.data.repository.MoodRepositoryImpl
import com.ymorenoanz.seren.domain.repository.MoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMoodRepository(
        impl: MoodRepositoryImpl
    ): MoodRepository
}