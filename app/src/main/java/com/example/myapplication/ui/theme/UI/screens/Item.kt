package com.example.myapplication.ui.theme.UI.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Item
import com.example.myapplication.ui.theme.YandexDiskUserInfo

//@Preview(showBackground = true)
@Composable
fun Item(item:Item) {
    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp),
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
//                modifier = Modifier.background(Color.Gray)

                )

            Row(
                Modifier
                    .fillMaxWidth()
                    /*.background(Color.Red)*/,
                horizontalArrangement = Arrangement.End

            ) {

            Image(painter = painterResource(id = R.drawable.baseline_delete_forever_24)
                , contentDescription = "Name")
        }
            }

    }

}
