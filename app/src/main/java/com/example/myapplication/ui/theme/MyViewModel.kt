package com.example.myapplication.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.theme.API.ApiService
import com.example.myapplication.ui.theme.API.Constante
import com.example.myapplication.ui.theme.Data.Item
import com.example.myapplication.ui.theme.Data.YandexDiskUserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _isLoadFile: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoadFile: StateFlow<Boolean> = _isLoadFile.asStateFlow()

    private val _loadingFile: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingFile: StateFlow<Boolean> = _loadingFile.asStateFlow()

    private val _fileYA: MutableStateFlow<YandexDiskUserInfo?> = MutableStateFlow(null)
    val fileYA: StateFlow<YandexDiskUserInfo?> = _fileYA.asStateFlow()

    private val _test: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val test: StateFlow<Boolean> = _test.asStateFlow()


    init {
        startLoadingFile()
        android.util.Log.d("MyLog", "MyViewModel.kt.  INIT: ")
    }


    fun refreshLoadingFile() {
        viewModelScope.launch {
            _loadingFile.value = true
            startLoadingFile()
            delay(1500)
            _loadingFile.value = false
        }
        android.util.Log.d("MyLog", "MyViewModel.kt. refreshLoadingFile:  refresh")
    }


    fun startLoadingFile() {
        if (isLoadFile.value) {
            viewModelScope.launch {
                val result = apiService.getUserInfo(
                    Constante.authToken, Constante.url_info
                )

                if (result.isSuccessful) {
                    _fileYA.value = result.body()
                } else {
                    throw (Exception("Ошибка, код ошибки ${result.code()}"))
                }
            }


        }
    }


    fun deleteItem(item: Item) {
        viewModelScope.launch {
                val body= apiService.deleteFile(Constante.authToken, Constante.url_delete + item.path)
            if (body.isSuccessful) { startLoadingFile()}

        }

    }
}