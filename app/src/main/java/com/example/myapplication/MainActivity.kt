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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember


import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var apiServiccce: ApiService
     var myRespons= MutableStateFlow<YandexDiskUserInfo?>(null)

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isload = MutableStateFlow(false)
        lifecycleScope.launch {

            val authToken = "y0_AgAAAAAC82upAADLWwAAAADlltdLOtUDPw_5RoqBUKqEbmjZZScvdtg"
            val scopeLoad= CoroutineScope(Dispatchers.IO).launch {
            val respons=apiServiccce.getUserInfo(
                authToken,
                "resources?path=%D0%A0%D0%B0%D0%B1%D0%BE%D1%82%D0%B0%2F%D0%A0%D0%B0%D1%86%20%D0%A1%D0%BC%D0%B5%D1%82%D0%B0%2FUserName&fields=60&sort=-created"
            )
            if (respons.isSuccessful){
                myRespons.value= respons.body()!!
                withContext(Dispatchers.Main){
                }
            }
        }
            scopeLoad.join()
            if(scopeLoad.isCompleted) {

                isload.value=true
            }
            myRespons.collect{

                Log.d("MyLog", "onCreate: $it")
            }
        }

        setContent {
            val navController = rememberNavController()
           NavHost(navController = navController, startDestination = "Splash"){
               composable("Splash"){
                    Splash(navController = navController,isload)
                   }

               composable("Home"){
                   val myData = remember{ mutableStateOf<YandexDiskUserInfo?>(null)}


                   LaunchedEffect(Unit){
                       myRespons.collect{it->
                      if (it != null) {
                          myData.value=it
                      }
                  }
                   }
                   myData.value?.let { it1 -> GreetingPreview(it1) }

               }
           }

           }
        }

    }




@Composable
fun GreetingPreview(respons: YandexDiskUserInfo)
{

    var mytext by remember { mutableStateOf("Привет")    }
    mytext=respons._embedded.items[1].name


    Column() {
        LazyColumn(modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.5f)
            .padding(10.dp),


        )
        {
            items(respons._embedded.items){it->
                Text(text = it.name,
                    fontSize = 15.sp)
            }

        }

            Text(text = mytext)



        SubcomposeAsyncImage(
            model = "https://wallscloud.net/img/resize/3200/2400/MM/2023-07-26-seealpsee-switzerland-1-59808.jpeg",
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = null
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://sun59-1.userapi.com/impg/vybz3822oXofA2SyKZOPNcqWKLx-aQj3vHl2jA/0H6HMJ1TF0M.jpg?size=1865x2160&quality=95&sign=09fc4e24e9c53497aa8aba5552ef4b8c&type=album")
                .crossfade(true)
                .build(),
            placeholder = painterResource(androidx.core.R.drawable.ic_call_answer_low),
            error = painterResource(R.drawable.cat32),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )

    }


    }












