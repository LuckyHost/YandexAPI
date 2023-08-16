package com.example.myapplication.domain

import com.example.myapplication.domain.retrofit.DataClass.*
import com.example.myapplication.domain.room.entity.*
import kotlinx.coroutines.flow.*
import retrofit2.*

interface Repository {

    suspend fun startLoadingFile(token:String,code:(Int)->Unit): Response<YandexDiskUserInfo>

    suspend fun deleteData(token: String,item: Item): Response<YandexDiskUserInfo>
    suspend fun refreshLoadingFile(
        stateFlow: MutableStateFlow<Boolean>,
        startfun: Response<YandexDiskUserInfo>,
    )

    suspend fun getDatainDB():Flow<List<PersonInfo>>
}
