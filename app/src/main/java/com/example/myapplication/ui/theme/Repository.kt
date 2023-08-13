package com.example.myapplication.ui.theme

import com.example.myapplication.ui.theme.Data.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.Response

interface Repository {

    suspend fun startLoadingFile(): YandexDiskUserInfo

    suspend fun deleteData(item: Item): Response<YandexDiskUserInfo>

    suspend fun refreshLoadingFile(stateFlow: MutableStateFlow<Boolean>,startfun:Unit)
}