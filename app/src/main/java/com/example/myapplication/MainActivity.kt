package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.myapplication.ui.theme.API.ApiService
import com.example.myapplication.ui.theme.UI.screens.Splash

import com.example.myapplication.ui.theme.YandexDiskUserInfo
import dagger.hilt.android.AndroidEntryPoint


import javax.inject.Inject
import androidx.compose.runtime.*
import com.example.myapplication.ui.theme.UI.screens.Home
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiServiccce: ApiService
//     var myRespons= MutableStateFlow<YandexDiskUserInfo?>(null)

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun load():Flow<YandexDiskUserInfo> = flow{
            val authToken = "y0_AgAAAAAC82upAADLWwAAAADlltdLOtUDPw_5RoqBUKqEbmjZZScvdtg"
                val respons=apiServiccce.getUserInfo(
                    authToken,
                    "resources?path=%D0%A0%D0%B0%D0%B1%D0%BE%D1%82%D0%B0%2F%D0%A0%D0%B0%D1%86%20%D0%A1%D0%BC%D0%B5%D1%82%D0%B0%2FUserName&fields=60&sort=-created"
                )
                if (respons.isSuccessful){
                emit(respons.body()!!)
                }
            else {
                    throw (Exception("Ошибка, код ошибки ${respons.code()}"))
            }
        }



        setContent {

            val test = remember { mutableStateOf<YandexDiskUserInfo?>(null) }

            LaunchedEffect(true){

            load().collect{
                test.value=it
            }
            }
/*
            val load =load().collectAsState(Result)
            val load2=load.value as? YandexDiskUserInfo*/

            Log.d("MyLog", "onCreate: ${test}.")
            val isLoad = remember { mutableStateOf(false)}
    //
            if (test !=null){
                isLoad.value=true
            }


            val navController = rememberNavController()
           NavHost(navController = navController, startDestination = "Splash"){
               composable("Splash"){
                    Splash(navController = navController,isLoad)
                   }

               composable("Home"){
                   Home(yandexDiskUserInfo = test.value!!)
                }

               }


           }
        }

    }

















