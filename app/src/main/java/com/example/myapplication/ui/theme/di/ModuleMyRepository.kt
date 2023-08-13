package com.example.myapplication.ui.theme.di

import com.example.myapplication.ui.theme.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleMyRepository {

    @Singleton
    @Provides
    fun provideRepository_Impl(repository: Repository_Impl):Repository{
        return repository
    }
}