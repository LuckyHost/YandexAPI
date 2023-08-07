package com.example.myapplication.ui.theme.UI.screens

import android.util.Log
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.myapplication.R
import com.example.myapplication.ui.theme.API.ApiService
import com.example.myapplication.ui.theme.YandexDiskUserInfo
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun Home(yandexDiskUserInfo: State<YandexDiskUserInfo?>, apiServiccce:ApiService, isLoaderFile:MutableStateFlow<Boolean>,isLoadFile:MutableStateFlow<Boolean>) {
    var offSet = remember {mutableStateOf(-60f)}
    var isRotashion by remember {mutableStateOf(false)}


    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Red)
        .pointerInput(Unit){detectVerticalDragGestures {
                change, dragAmount ->
            android.util.Log.d("MyLog","Home.kt. Home: dragAmount $dragAmount")
            isLoadFile.value = true
        }}
    )
    {
        Sync(offSet,isRotashion)

        Column(modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectVerticalDragGestures { _, dragAmount ->
                    offSet.value = dragAmount
    //                Log.d("MyLog", "Home: detectVerticalDragGestures dragAmount ${offSet.value.dp}")
                    Log.d("MyLog", "Home: pointer $isRotashion")
                    if (dragAmount > 50) {
                        isRotashion = true
                        isLoadFile.value = true
                        android.util.Log.d("MyLog", "Home.kt. Home: ${isLoaderFile.value}")
                    }
                }
            }) {





            }

           LazyColumn(modifier = Modifier
               .background(Color.Blue)
               .fillMaxSize(0.5f)
               .pointerInput(Unit) {
                   detectVerticalDragGestures { change, dragAmount ->
                       android.util.Log.d("MyLog", "Home.kt. Home: dragAmount $dragAmount")
                   }
               }

            )
            {
                yandexDiskUserInfo.value?._embedded?.let {
                    items(it.items) {
                        Item(it,apiServiccce)
                    }
                }
            }

    }





    
/*    SubcomposeAsyncImage(
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
        placeholder = painterResource(R.drawable.ic_call_answer_low),
        error = painterResource(R.drawable.ic_call_answer_low),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape)
    )*/

}

@Composable
fun Sync(offSet: MutableState<Float>,isRotation: Boolean){

    val  rotation by  rememberInfiniteTransition().animateFloat(
        initialValue =0f,
        targetValue =360f ,
        animationSpec = InfiniteRepeatableSpec(tween(2000),RepeatMode.Reverse), label = ""
    )
    val myOffSet by animateFloatAsState(
        targetValue = offSet.value,
        animationSpec = spring(),
        label = ""
    )

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = myOffSet.dp)
    ) {


        Image(
            painter = painterResource(id = R.drawable.baseline_change_circle_24),
            contentDescription ="",
            modifier = Modifier
                .size(60.dp)
                .rotate(
                    if (isRotation) {
                        rotation
                    } else {
                        0f
                    }
                )

        )

    }
}