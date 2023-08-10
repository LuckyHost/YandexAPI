package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiServiccce: ApiService

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoadFile:MutableStateFlow<Boolean> = MutableStateFlow(false)
        val loadingFile:MutableStateFlow<Boolean> = MutableStateFlow(true)
        val loadFile:MutableStateFlow<YandexDiskUserInfo? > = MutableStateFlow(null)

           lifecycleScope.launch {
               loadingFile.collect{
                   if (it){
                     loadFile.value= CoroutineScope(Dispatchers.IO).async{
                        val respons=apiServiccce.getUserInfo(authToken,url_info)
                        if (respons.isSuccessful){
                            isLoadFile.value=true
                            delay(1500)
                            loadingFile.value=false
                             return@async respons.body()!!
                        }
                        else {
                            throw (Exception("Ошибка, код ошибки ${respons.code()}"))
                        }
                    }.await()
                    }
                  }
           }
                setContent {

                    val yaFile =loadFile.collectAsState()
                    val navController = rememberNavController()

                   NavHost(navController = navController, startDestination = "Splash"){
                       composable("Splash"){
                            Splash(navController = navController,isLoadFile)
                           }

                       composable("Home"){
                           Home(yaFile,apiServiccce,loadingFile)
                           Log.d("Mylog", "onCreate: ЧТо то изменилось в Home")
                        }
                       }
                   }

    }
}




















