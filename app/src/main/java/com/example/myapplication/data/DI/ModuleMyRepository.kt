package com.example.myapplication.data.DI

import com.example.myapplication.data.*
import com.example.myapplication.domain.*
import com.example.myapplication.present.*
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
    fun provideRepository_Impl(repository: Repository_Impl): Repository {
        return repository
    }
}