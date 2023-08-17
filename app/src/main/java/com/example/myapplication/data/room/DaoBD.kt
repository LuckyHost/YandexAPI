package com.example.myapplication.data.room

import androidx.room.*
import com.example.myapplication.domain.room.entity.*
import kotlinx.coroutines.flow.*

@Dao
interface DaoBD {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersonInfo(users: PersonInfo)
    @Query("DELETE  FROM PersonInfoTable")
    suspend fun deleteTable()
    @Query("SELECT * FROM PersonInfoTable")
    suspend fun getAll(): List<PersonInfo>

    @Query("SELECT * FROM PersonInfoTable WHERE id IN (:userId)")
    suspend fun findPerson (userId: Int): PersonInfo

}