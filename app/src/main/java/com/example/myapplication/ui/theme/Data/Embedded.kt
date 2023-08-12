package com.example.myapplication.ui.theme.Data

data class Embedded(
    val items: List<Item>,
    val limit: Int,
    val offset: Int,
    val path: String,
    val sort: String,
    val total: Int
)