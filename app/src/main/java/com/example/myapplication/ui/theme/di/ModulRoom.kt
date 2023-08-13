package com.example.myapplication.ui.theme.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.ui.theme.db.DAO.DaoBD
import com.example.myapplication.ui.theme.db.Data.MyDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ModulRoom {

    @Singleton
    @Provides
    fun provideMyDataBase(@ApplicationContext context: Context):MyDataBase{
        return  Room.databaseBuilder (
            context =  context.applicationContext,
            klass = MyDataBase::class.java,
            name = "DB.bd"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(myDataBase: MyDataBase):DaoBD{
        return myDataBase.getDao()
    }
}