package com.example.myapplication.ui.theme.UI.screens

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.myapplication.R
import com.example.myapplication.ui.theme.API.ApiService
import com.example.myapplication.ui.theme.Data.YandexDiskUserInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable

fun Home(
    yandexDiskUserInfo: State<YandexDiskUserInfo?>, apiServiccce:ApiService, loadingFile:MutableStateFlow<Boolean>
) {

    val refreshScope = rememberCoroutineScope()

    fun refresh() = refreshScope.launch {
        loadingFile.value=true
    }

    val state = rememberPullRefreshState(loadingFile.collectAsState().value, onRefresh = {refresh()})

Box(
    modifier = Modifier
        .pullRefresh(state),
)
        {

                       LazyColumn(modifier = Modifier
                       .fillMaxSize()

                    )
                    {
                        yandexDiskUserInfo.value?._embedded?.let {
                            items(it.items){
                                Item(item = it, apiService = apiServiccce,loadingFile)
                            }

                        }
                    }



                PullRefreshIndicator(loadingFile.collectAsState().value, state,Modifier.align(Alignment.TopCenter))

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


