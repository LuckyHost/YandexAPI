package com.example.myapplication.ui.theme.db.DAO
import androidx.room.Dao
import androidx.room.Insert
import com.example.myapplication.ui.theme.db.entity.PersonInfo

@Dao
interface DaoBD {

    fun getAll(): List<PersonInfo>
    @Insert
   suspend fun insertAll( users: PersonInfo)

}