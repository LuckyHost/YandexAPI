package com.example.myapplication.ui.theme.UI.screens

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.ui.theme.API.ApiService
import com.example.myapplication.ui.theme.YandexDiskUserInfo
import kotlinx.coroutines.flow.MutableStateFlow

import androidx.compose.animation.core.*


@Composable
fun Home(yandexDiskUserInfo: YandexDiskUserInfo, apiServiccce:ApiService) {


    Column(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectVerticalDragGestures { _, dragAmount ->
                if (dragAmount > 0) {

                    Log.d("MyLog", "Home: pointer true")
                }
            }
        }) {Sync()





//    LazyColumn(modifier = Modifier
//
//        .fillMaxWidth()
//    )
//    {
//        items(yandexDiskUserInfo._embedded.items){
//        Item(it,apiServiccce)
//        }
//
//
//    }
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
fun Sync(){
   

    val  rotation by  rememberInfiniteTransition().animateFloat(
        initialValue =0f,
        targetValue =360f ,
        animationSpec = InfiniteRepeatableSpec(tween(2000),RepeatMode.Reverse), label = ""
    )

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()) {


        Image(
            painter = painterResource(id = R.drawable.baseline_change_circle_24),
            contentDescription ="312",
            modifier = Modifier
                .size(60.dp)
                .rotate(rotation)
                .offset(y = 10.dp)

        )

    }
}