package com.example.myapplication.data

import com.example.myapplication.data.NetWork.*
import com.example.myapplication.domain.*
import com.example.myapplication.domain.retrofit.DataClass.*
import kotlinx.coroutines.flow.*
import retrofit2.*
import javax.inject.*

class Repository_Impl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun startLoadingFile(): Response<YandexDiskUserInfo> {
        val result = apiService.getUserInfo(
            Constante.authToken, Constante.url_info
        )
        if (result.isSuccessful) {
            return result
        } else {
            throw (Exception("Ошибка, код ошибки ${result.code()}"))
        }


    }

    override suspend fun deleteData(item: Item): Response<YandexDiskUserInfo> {
        return apiService.deleteFile(
            Constante.authToken, Constante.url_delete + item.path
        )

    }

    override suspend fun refreshLoadingFile(
        stateFlow: MutableStateFlow<Boolean>,
        startfun: Response<YandexDiskUserInfo>,
    ) {
        if (startfun.isSuccessful) {
            stateFlow.value = false
        }


    }
}