package com.example.myapplication.data.DI

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.room.DaoBD
import com.example.myapplication.domain.room.db.MyDataBase
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
    fun provideMyDataBase(@ApplicationContext context: Context): MyDataBase {
        return  Room.databaseBuilder (
            context =  context.applicationContext,
            klass = MyDataBase::class.java,
            name = "DB.bd"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(myDataBase: MyDataBase): DaoBD {
        return myDataBase.getDao()
    }
}