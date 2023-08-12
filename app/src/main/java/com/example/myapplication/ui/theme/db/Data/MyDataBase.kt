package com.example.myapplication.ui.theme.db.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.ui.theme.db.DAO.DaoBD
import com.example.myapplication.ui.theme.db.entity.PersonInfo

@Database([PersonInfo::class], version = 1)
abstract class MyDataBase: RoomDatabase() {

    abstract   fun getDao(): DaoBD
}