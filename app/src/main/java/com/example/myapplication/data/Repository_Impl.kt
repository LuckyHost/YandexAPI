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

    override suspend fun startLoadingFile(isSuccessful: (Boolean) -> Unit): Response<YandexDiskUserInfo> {
        val result = apiService.getUserInfo(
            Constante.authToken, Constante.url_info
        )
        Log.d("MyLog","Функция start")
        isSuccessful(result.isSuccessful)
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