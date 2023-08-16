package com.example.myapplication.data

import android.util.*
import com.example.myapplication.data.NetWork.*
import com.example.myapplication.data.room.*
import com.example.myapplication.domain.*
import com.example.myapplication.domain.retrofit.DataClass.*
import com.example.myapplication.domain.room.entity.*
import kotlinx.coroutines.flow.*
import retrofit2.*
import timber.log.*
import javax.inject.*


class Repository_Impl @Inject constructor(
    private val apiService: ApiService,
    private val daoBD: DaoBD,
) : Repository {

    override suspend fun startLoadingFile(token: String,code: (Int) -> Unit): Response<YandexDiskUserInfo> {
        val result = apiService.getUserInfo(token, Constante.url_info)
        code(result.code())
        return result
        }





    override suspend fun deleteData(token: String,item: Item): Response<YandexDiskUserInfo> {
        return apiService.deleteFile(
            token, Constante.url_delete + item.path
        )

    }

    override suspend fun getDatainDB(): Flow<List<PersonInfo>> {
        return  flow{

            emit(daoBD.getAll())
        }
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