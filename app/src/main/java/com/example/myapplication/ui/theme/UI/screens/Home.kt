package com.example.myapplication.ui.theme.UI.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.R
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.myapplication.ui.theme.API.ApiService
import com.example.myapplication.ui.theme.YandexDiskUserInfo
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun Home(yandexDiskUserInfo: YandexDiskUserInfo, apiServiccce:ApiService) {


    Column {


 /*   Button(onClick =
    { isUpdate.value = !isUpdate.value
        Log.d("MyLog",isUpdate.value.toString())
    }) {
        Text(text = "Click ${isUpdate.value}")

    }*/
    LazyColumn(modifier = Modifier.fillMaxWidth()
    )
    {
        items(yandexDiskUserInfo._embedded.items){
        Item(it,apiServiccce)
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