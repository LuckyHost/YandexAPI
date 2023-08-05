package com.example.myapplication.ui.theme

data class YandexDiskUserInfo(
    val _embedded: Embedded,
    val comment_ids: CommentIdsX,
    val created: String,
    val exif: Exif,
    val modified: String,
    val name: String,
    val path: String,
    val public_key: String,
    val public_url: String,
    val resource_id: String,
    val revision: Long,
    val type: String
)