package com.example.myapplication.ui.theme

import com.example.myapplication.ui.theme.API.*
import com.example.myapplication.ui.theme.Data.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.*
import javax.inject.*

class Repository_Impl @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun startLoadingFile(): YandexDiskUserInfo {
        val result = apiService.getUserInfo(
            Constante.authToken, Constante.url_info
        )
        if (result.isSuccessful) {
            return result.body()!!
        } else {
            throw (Exception("Ошибка, код ошибки ${result.code()}"))
        }


    }

    override suspend fun deleteData(item:Item): Response<YandexDiskUserInfo> {
        return  apiService.deleteFile(
            Constante.authToken, Constante.url_delete + item.path
        )

    }

    override suspend fun refreshLoadingFile(stateFlow: MutableStateFlow<Boolean>, startfun: Unit) {
        stateFlow.value = true
        startfun
        delay(1500)
        stateFlow.value = false
    }
}