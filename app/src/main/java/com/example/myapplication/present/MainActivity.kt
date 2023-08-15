package com.example.myapplication.present


import android.os.*
import android.util.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.NetWork.ApiService
import com.example.myapplication.data.room.DaoBD
import com.example.myapplication.domain.room.db.MyDataBase
import com.example.myapplication.present.UI.screens.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var apiServiccce: ApiService

    @Inject lateinit var db: MyDataBase

    @Inject lateinit var dao: DaoBD
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MyViewModel by viewModels()
//        viewModel.startLoadingFile()


        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Login") {
                composable("Login") {
                    Login(navController,viewModel)
                }
                composable("Splash") {
                    Splash(navController, viewModel)
                }
                composable("Home") {
                    Home(viewModel)
                }
            }


        }

    }

    override fun onStop() {
        super.onStop()
        Log.d("MyLog", "MainActivity.kt. onStop: Stop")
        CoroutineScope(Dispatchers.IO).launch {
            db.clearAllTables()
//        dao.deleteTable()
        }
    }


}




















