package com.example.myapplication.ui.theme.API

import com.example.myapplication.ui.theme.YandexDiskUserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url
interface ApiService {
    @GET()
    suspend fun getUserInfo(@Header("Authorization") authToken: String, @Url path :String): Response<YandexDiskUserInfo>
}