package com.example.myapplication.data.NetWork

import com.example.myapplication.domain.retrofit.DataClass.YandexDiskUserInfo
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url
interface ApiService {
    @GET()
//    suspend fun getUserInfo(@Header("Authorization") authToken: String, @Url path :String): ApiResponse<YandexDiskUserInfo>
    suspend fun getUserInfo(@Header("Authorization") authToken: String, @Url path :String): Response<YandexDiskUserInfo>

    @DELETE()
    suspend fun deleteFile(@Header("Authorization") authToken: String, @Url path :String): Response<YandexDiskUserInfo>

}