package com.example.myapplication.ui.theme.UI.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.API.Constante.authToken
import com.example.myapplication.ui.theme.API.Constante.url_delete
import com.example.myapplication.ui.theme.Data.Item
import com.example.myapplication.ui.theme.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


//@Preview(showBackground = true)
@Composable

fun Item(item: Item, myViewModel: MyViewModel) {



    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, start = 6.dp, end = 6.dp)
        .clickable { }
     ,
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
                fontSize = 20.sp
                )

            Row(
                Modifier
                    .fillMaxWidth()
                    /*.background(Color.Red)*/,
                horizontalArrangement = Arrangement.End

            ) {
                IconButton(
                    onClick = {myViewModel.deleteItem(item)
                    myViewModel.startLoadingFile()}
                )
                {
                    Icon(Icons.Default.Delete,"Delete Item" )
                }


        }
            }

    }




}
