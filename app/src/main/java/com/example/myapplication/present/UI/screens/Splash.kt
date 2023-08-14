package com.example.myapplication.present.UI.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.present.MyViewModel
import com.example.myapplication.present.Pink40
import com.example.myapplication.present.PurpleGrey40
import kotlinx.coroutines.delay

//@Preview (showBackground = true)
@Composable
 fun Splash(navController: NavController,myViewModel: MyViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Pink40),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {

        Text(
            text = "Loading...",
            color = Color.White,
            fontSize = 25.sp
        )
        LinearProgressIndicator(
            modifier = Modifier
                .width(200.dp)
                .height(4.dp),
            color = PurpleGrey40,
        )

        LaunchedEffect(true) {
            myViewModel.isLoadFile.collect {
                if (it) {
                    android.util.Log.d("MyLog","Splash.kt. Splash: $it")
                    delay(1000)
                    navController.popBackStack()
                    navController.navigate("Home")
                }
            }

        }

    }
}
