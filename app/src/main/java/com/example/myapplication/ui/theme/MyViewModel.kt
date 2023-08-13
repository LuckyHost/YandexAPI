package com.example.myapplication.ui.theme

import android.util.*
import androidx.lifecycle.*
import com.example.myapplication.ui.theme.Data.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.*
import javax.inject.*

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: Repository_Impl,
) : ViewModel() {

    private val _isLoadFile: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadFile: StateFlow<Boolean> = _isLoadFile.asStateFlow()

    private val _loadingFile: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingFile: StateFlow<Boolean> = _loadingFile.asStateFlow()

    private val _fileYA: MutableStateFlow<YandexDiskUserInfo?> = MutableStateFlow(null)
    val fileYA: StateFlow<YandexDiskUserInfo?> = _fileYA.asStateFlow()


    fun refreshLoadingFile() {
        viewModelScope.launch {
            _loadingFile.value=true
          val startfun=startLoadingFile()
            repository.refreshLoadingFile(_loadingFile, startfun.await())
        }
    }

    fun startLoadingFile(): Deferred<Response<YandexDiskUserInfo>> {
        return viewModelScope.async {
            _fileYA.value = repository.startLoadingFile().body()
            _isLoadFile.value = true
            return@async repository.startLoadingFile()
        }

    }


    fun deleteItem(item: Item) {
        viewModelScope.launch {
            if (repository.deleteData(item).isSuccessful) startLoadingFile() else
                Log.d(
                    "MyLog",
                    "MyViewModel.kt. deleteItem: ${repository.deleteData(item).code()}"
                )
        }

    }
}

