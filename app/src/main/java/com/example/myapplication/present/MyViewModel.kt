package com.example.myapplication.present

import androidx.lifecycle.*
import com.example.myapplication.domain.*
import com.example.myapplication.domain.retrofit.DataClass.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.*
import javax.inject.*

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _isLoadFile: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadFile: StateFlow<Boolean> = _isLoadFile.asStateFlow()

    private val _loadingFile: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingFile: StateFlow<Boolean> = _loadingFile.asStateFlow()

    private val _fileYA: MutableStateFlow<YandexDiskUserInfo?> = MutableStateFlow(null)
    val fileYA: StateFlow<YandexDiskUserInfo?> = _fileYA.asStateFlow()

    private val _token: MutableStateFlow<String> = MutableStateFlow("")
    val token: StateFlow<String?> = _token.asStateFlow()


    fun insertToken(token:String){
        _token.value=token

    }

    fun refreshLoadingFile() {
        viewModelScope.launch {
            _loadingFile.value=true
          val startFun=startLoadingFile(){}
            repository.refreshLoadingFile(_loadingFile, startFun.await())
        }
    }

    fun startLoadingFile(code:(Int)->Unit): Deferred<Response<YandexDiskUserInfo>> {
        return viewModelScope.async {
            val response = repository.startLoadingFile(token.value.toString()){ code(it) }
            _fileYA.value = response.body()
            _isLoadFile.value = true
            return@async response
        }

    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            if (repository.deleteData(token.value!!,item).isSuccessful)  startLoadingFile(){}else
                null
        }

    }
}

