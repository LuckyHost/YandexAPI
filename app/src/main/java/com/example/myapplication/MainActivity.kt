package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiServiccce: ApiService

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoaderFile:MutableStateFlow<Boolean> = MutableStateFlow(false)
        val YandexFile:MutableStateFlow<YandexDiskUserInfo? > = MutableStateFlow(null)

        val loadFile= CoroutineScope(Dispatchers.IO).async{
            val respons=apiServiccce.getUserInfo(authToken,url_info)
            if (respons.isSuccessful){
                isLoaderFile.value=true
                 return@async respons.body()
            }
            else {
                throw (Exception("Ошибка, код ошибки ${respons.code()}"))
            }
        }
                setContent {
                    val yaFile=YandexFile.collectAsState()
                    val navController = rememberNavController()

                    LaunchedEffect(true){
                            YandexFile.value = loadFile.await()
                        isLoaderFile.collect{
                            Log.d("MyLog", "onCreate: $it"+" Collect")
                        }
                    }

                   NavHost(navController = navController, startDestination = "Splash"){
                       composable("Splash"){

                            Splash(navController = navController,isLoaderFile)

                           Log.d("Mylog", "onCreate: ЧТо то изменилось в Splash")
                           }

                       composable("Home"){

                           Home(yandexDiskUserInfo = yaFile.value!!,apiServiccce)
                           Log.d("Mylog", "onCreate: ЧТо то изменилось в Home")
                        }

                       }



                   }

    }
}




















