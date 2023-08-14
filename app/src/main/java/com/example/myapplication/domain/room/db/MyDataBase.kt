package com.example.myapplication.domain.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.room.DaoBD
import com.example.myapplication.domain.room.entity.PersonInfo

@Database([PersonInfo::class], version = 1)
abstract class MyDataBase: RoomDatabase() {
    abstract fun getDao(): DaoBD
}