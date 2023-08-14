package com.example.myapplication.data.room
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.domain.room.entity.PersonInfo

@Dao
interface DaoBD {
    @Query("SELECT * FROM MyTable")
     fun getAll(): List<PersonInfo>

    @Insert
   suspend fun insertAll( users: PersonInfo)

    @Query("DELETE  FROM MyTable")
    suspend fun deleteTable()

}