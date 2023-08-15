package com.example.myapplication.data.room

import androidx.room.*
import com.example.myapplication.domain.room.entity.*
import kotlinx.coroutines.flow.*

@Dao
interface DaoBD {
    @Insert
    suspend fun insertAll(users: PersonInfo)
    @Query("DELETE  FROM MyTable")
    suspend fun deleteTable()
    @Query("SELECT * FROM MyTable")
    suspend fun getAll(): List<PersonInfo>

}