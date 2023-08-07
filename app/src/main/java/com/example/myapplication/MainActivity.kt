package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState


import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.API.ApiService
import com.example.myapplication.ui.theme.UI.screens.Splash

import com.example.myapplication.ui.theme.YandexDiskUserInfo
import dagger.hilt.android.AndroidEntryPoint


import javax.inject.Inject
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.ui.theme.API.Constante.authToken
import com.example.myapplication.ui.theme.API.Constante.url_info
import com.example.myapplication.ui.theme.UI.screens.Home
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiServiccce: ApiService

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoaderFile:MutableStateFlow<Boolean> = MutableStateFlow(false)
        val isLoadFile:MutableStateFlow<Boolean> = MutableStateFlow(true)
        val YandexFile:MutableStateFlow<YandexDiskUserInfo? > = MutableStateFlow(null)
        val loadFile:MutableStateFlow<Deferred<YandexDiskUserInfo>?> = MutableStateFlow(null)

           lifecycleScope.launch {
               isLoadFile.collect{
                   android.util.Log.d("MyLog","MainActivity.kt. onCreate: $it")

                    if (it){
                     loadFile.value= CoroutineScope(Dispatchers.IO).async{
                        val respons=apiServiccce.getUserInfo(authToken,url_info)
                        if (respons.isSuccessful){
                            isLoaderFile.value=true
                            isLoadFile.value=false
                             return@async respons.body()!!
                        }
                        else {
                            throw (Exception("Ошибка, код ошибки ${respons.code()}"))
                        }
                    }
                    }
                  }
           }
                setContent {
                    val yaFile=YandexFile.collectAsState()
                    val navController = rememberNavController()

//                    LaunchedEffect(isLoadFile.collectAsState().value){
//                            YandexFile.value = loadFile.value?.await()
//                    }

                   NavHost(navController = navController, startDestination = "Splash"){
                       composable("Splash"){
                            Splash(navController = navController,isLoaderFile)
                           Log.d("Mylog", "onCreate: ЧТо то изменилось в Splash")
                           }

                       composable("Home"){
                           Home(yaFile,apiServiccce,isLoaderFile,isLoadFile)
                           Log.d("Mylog", "onCreate: ЧТо то изменилось в Home")
                        }
                       }
                   }

    }
}




















