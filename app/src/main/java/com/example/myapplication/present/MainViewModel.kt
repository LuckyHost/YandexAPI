package com.example.myapplication.present

import android.util.*
import androidx.lifecycle.*
import com.example.myapplication.data.room.*
import com.example.myapplication.domain.*
import com.example.myapplication.domain.retrofit.DataClass.*
import com.example.myapplication.domain.room.entity.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.*
import java.net.*
import javax.inject.*

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val daoBD: DaoBD,
) : ViewModel() {

    private val _isLoadFile: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadFile: StateFlow<Boolean> = _isLoadFile.asStateFlow()

    private val _loadingFile: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingFile: StateFlow<Boolean> = _loadingFile.asStateFlow()

    private val _fileYA: MutableStateFlow<YandexDiskUserInfo?> = MutableStateFlow(null)
    val fileYA: StateFlow<YandexDiskUserInfo?> = _fileYA.asStateFlow()


    private val _token: MutableStateFlow<String> = MutableStateFlow("")
    val token: StateFlow<String> = _token.asStateFlow()

    private val _path: MutableStateFlow<String> = MutableStateFlow("")
    val path: StateFlow<String> = _path.asStateFlow()



    fun refreshLoadingFile() {
        viewModelScope.launch {
            _loadingFile.value=true
          val startFun=startLoadingFile(){}
            repository.refreshLoadingFile(_loadingFile, startFun.await())
        }
    }

    fun startLoadingFile(code:(Int)->Unit): Deferred<Response<YandexDiskUserInfo>> {

        return viewModelScope.async {
            val response = repository.startLoadingFile(
                daoBD.findPerson(0).token,
                daoBD.findPerson(0).path
            ) { code(it) }
            _fileYA.value = response.body()
            _isLoadFile.value = true
            return@async response
        }

    }

    fun deleteItem(item: Item) {
        viewModelScope.async {
            if (repository.deleteData(token.value, item).isSuccessful) startLoadingFile {} else
                null
        }

    }

    fun insertTokenAndPath(token: String, path: String) {
        viewModelScope.launch {
            daoBD.insertPersonInfo(PersonInfo(0, "", token, path, false))

        }
    }


}

