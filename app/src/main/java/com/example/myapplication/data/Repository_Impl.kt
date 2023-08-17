package com.example.myapplication.data

import com.example.myapplication.data.NetWork.*
import com.example.myapplication.data.room.*
import com.example.myapplication.domain.*
import com.example.myapplication.domain.retrofit.DataClass.*
import com.example.myapplication.domain.room.entity.*
import kotlinx.coroutines.flow.*
import retrofit2.*
import javax.inject.*


class Repository_Impl @Inject constructor(
    private val apiService: ApiService,
) : Repository {

    override suspend fun startLoadingFile(
        token: String,
        path: String,
        code: (Int) -> Unit,
    ): Response<YandexDiskUserInfo> {
        val result = apiService.getUserInfo(token, path)
        code(result.code())
        return result
    }


    override suspend fun deleteData(token: String, item: Item): Response<YandexDiskUserInfo> {
        return apiService.deleteFile(
            token, Constante.url_delete + item.path
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

    override suspend fun creatDbRoom(): Flow<List<PersonInfo>> {
        TODO("Not yet implemented")
    }
}