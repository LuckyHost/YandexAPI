package com.example.myapplication.domain.room.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("MyTable")
data class PersonInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name:String,
    val API:String
)
