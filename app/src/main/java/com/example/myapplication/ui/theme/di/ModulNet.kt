package com.example.myapplication.ui.theme.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.ui.theme.API.ApiService
import com.example.myapplication.ui.theme.db.Data.MyDataBase
import com.example.myapplication.ui.theme.db.DAO.DaoBD
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModulNet {


    @Singleton
    @Provides
    fun provideReteofit(okHttpClient: OkHttpClient):Retrofit{
         val BASE_URL = "https://cloud-api.yandex.net/v1/disk/"
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideokHttpClient():OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context):MyDataBase{
        return  Room.databaseBuilder(
                context.applicationContext,
                MyDataBase::class.java,
                "DB.db").build()
        }

    @Singleton
    @Provides
    fun provideDAO(db:MyDataBase): DaoBD = db.getDao()




}