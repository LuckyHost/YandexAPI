package com.example.myapplication.ui.theme.db.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersonInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name:String,
    val API:String
)
