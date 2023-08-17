package com.example.myapplication.domain.room.entity
import android.util.JsonToken
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("PersonInfoTable")
data class PersonInfo(
    @PrimaryKey(autoGenerate = false)
    val id: Int=0,
    val name:String,
    val token:String,
    val path:String,
    val isLogin:Boolean
)
