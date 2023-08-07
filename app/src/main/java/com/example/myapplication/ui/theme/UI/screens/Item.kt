package com.example.myapplication.ui.theme.UI.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButtonDefaults.elevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.API.ApiService
import com.example.myapplication.ui.theme.API.Constante
import com.example.myapplication.ui.theme.API.Constante.authToken
import com.example.myapplication.ui.theme.API.Constante.url_delete
import com.example.myapplication.ui.theme.Item
import com.example.myapplication.ui.theme.YandexDiskUserInfo
import com.example.myapplication.ui.theme.di.Modul
import dagger.Component
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


//@Preview(showBackground = true)
@Composable

fun Item(item:Item,apiService: ApiService) {
    val onImageClick: (item:Item) -> Unit = {
        CoroutineScope(Dispatchers.IO).launch {

            apiService.deleteFile(authToken, url_delete+it.path)
        }

    }


    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)
        .pointerInput(Unit) {
            detectHorizontalDragGestures { change, dragAmount ->
                android.util.Log.d("MyLog","Item.kt. Item: detectHorizontalDragGestures  $dragAmount")
            }
            detectVerticalDragGestures { change, dragAmount ->
                android.util.Log.d("MyLog","Item.kt. Item: detectVerticalDragGestures $dragAmount")
            }
        },
        elevation= CardDefaults.cardElevation(
            defaultElevation = 15.dp
        )
    )
    {
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(9.dp)
            /*.background(Color.Blue)*/,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        )
        {
            Text(text = item.name.dropLast(4),
                fontSize = 20.sp,
                )

            Row(
                Modifier
                    .fillMaxWidth()
                    /*.background(Color.Red)*/,
                horizontalArrangement = Arrangement.End

            ) {

            Image(
                painter = painterResource(id = R.drawable.baseline_delete_forever_24),
                contentDescription = "Name",
                Modifier.clickable { onImageClick(item) }
            )

        }
            }

    }




}
