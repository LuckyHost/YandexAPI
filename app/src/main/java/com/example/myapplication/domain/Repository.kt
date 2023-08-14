package com.example.myapplication.domain

import com.example.myapplication.domain.retrofit.DataClass.*
import kotlinx.coroutines.flow.*
import retrofit2.Response

interface Repository {

    suspend fun startLoadingFile(): Response<YandexDiskUserInfo>

    suspend fun deleteData(item: Item): Response<YandexDiskUserInfo>

    suspend fun refreshLoadingFile(stateFlow: MutableStateFlow<Boolean>,startfun:Response<YandexDiskUserInfo>)
}