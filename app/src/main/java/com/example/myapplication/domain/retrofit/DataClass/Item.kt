package com.example.myapplication.domain.retrofit.DataClass

data class Item(
    val antivirus_status: String,
    val comment_ids: CommentIdsX,
    val created: String,
    val exif: Exif,
    val `file`: String,
    val md5: String,
    val media_type: String,
    val mime_type: String,
    val modified: String,
    val name: String,
    val path: String,
    val resource_id: String,
    val revision: Long,
    val sha256: String,
    val size: Int,
    val type: String
)