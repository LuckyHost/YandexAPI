package com.example.myapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.API.ApiService
import com.example.myapplication.ui.theme.MyViewModel
import com.example.myapplication.ui.theme.UI.screens.Home
import com.example.myapplication.ui.theme.UI.screens.Splash
import com.example.myapplication.ui.theme.db.DAO.DaoBD
import com.example.myapplication.ui.theme.db.Data.MyDataBase
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




        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "Splash") {
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
        android.util.Log.d("MyLog", "MainActivity.kt. onStop: Stop")
        CoroutineScope(Dispatchers.IO).launch {
            db.clearAllTables()
//        dao.deleteTable()
        }
    }


}




















