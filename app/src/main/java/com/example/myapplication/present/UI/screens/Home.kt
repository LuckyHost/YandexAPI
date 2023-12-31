package com.example.myapplication.present.UI.screens

import android.annotation.*
import android.util.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.domain.retrofit.DataClass.Item
import com.example.myapplication.present.MainViewModel

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(myViewModel: MainViewModel) {

    val state = rememberPullRefreshState( myViewModel.loadingFile.collectAsState().value, onRefresh = { myViewModel.refreshLoadingFile() })
    val rememberYaFile=myViewModel.fileYA.collectAsState().value?._embedded?.items
    val myItems: List<Item>? by  rememberUpdatedState(rememberYaFile)

        Box(
            modifier = Modifier
                .pullRefresh(state),
        )
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            )
            {
                items(myItems!!) {
                    Item(it, myViewModel)
                }
            }

            PullRefreshIndicator(
                myViewModel.loadingFile.collectAsState().value,
                state,
                Modifier.align(Alignment.TopCenter)
            )

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


