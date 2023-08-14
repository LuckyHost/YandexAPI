package com.example.myapplication.present.UI.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import com.example.myapplication.domain.retrofit.DataClass.*
import com.example.myapplication.present.*


//@Preview(showBackground = true)
@Composable

fun Item(item: Item, myViewModel: MyViewModel,) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 6.dp, end = 6.dp)
            ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp
        )
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(9.dp)
            /*.background(Color.Blue)*/,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        )
        {
            Text(
                text = item.name.dropLast(4),
                fontSize = 20.sp
            )

            Row(
                Modifier
                    .fillMaxWidth()
                /*.background(Color.Red)*/,
                horizontalArrangement = Arrangement.End

            ) {
                IconButton(
                    onClick = {
                        myViewModel.deleteItem(item)
                        myViewModel.startLoadingFile()
                    }
                )
                {
                    Icon(Icons.Default.Delete, "Delete Item")
                }


            }
        }

    }


}
