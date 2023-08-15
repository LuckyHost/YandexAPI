package com.example.myapplication.present.UI.screens

import android.util.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import com.example.myapplication.R
import com.example.myapplication.present.*
import com.example.myapplication.present.theme.*
import kotlinx.coroutines.*
import timber.log.*

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController, viewModel: MyViewModel) {

    var tokenText by remember { mutableStateOf("") }
    var pathText by remember { mutableStateOf("") }
    var isLoadFile by remember { mutableStateOf(false) }


    LaunchedEffect(isLoadFile){

      if (isLoadFile) {navController.navigate("Home"); Log.d("MyLog","Циклит") }
    }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PurpleGrey40),
        contentAlignment = Alignment.Center

    )

    {

        Box(
            Modifier
//                .background(Color.Red)
                .fillMaxWidth(0.8f),
            contentAlignment = Alignment.Center
        )

        {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {

                Text(
                    text = "Статистика.YA",
                    fontSize = 25.sp
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(85.dp)
                )


                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = tokenText,
                    onValueChange = { tokenText = it },
                    label = { Text("API Token") },
                    singleLine = true,
                    placeholder = { Text("by Makarov.D") },
                    leadingIcon = {
                        Icon(Icons.Outlined.Lock, contentDescription = null)
                    },
                    trailingIcon = {
                        hideClearIcon(!tokenText.isEmpty()) { tokenText = "" }
                    }


                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = pathText,
                    onValueChange = { pathText = it },
                    label = { Text("Путь к папке на Ya.Disk") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Outlined.Info, contentDescription = null)
                    },
                    trailingIcon = {
                        hideClearIcon(!pathText.isEmpty()) { pathText = "" }
                    },
                    enabled = !tokenText.isEmpty()
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )


                ElevatedAssistChip(
                    onClick = { viewModel.startLoadingFile(){isLoadFile=it} },
                    label = { Text("Войти") },
                    leadingIcon = {

                            if (isLoadFile) {
                                Icon( Icons.Filled.Done,
                                    contentDescription = "Localized description",
                                    Modifier.size(AssistChipDefaults.IconSize))
                            }
                            else{
                                Icon( Icons.Filled.KeyboardArrowRight,
                                    contentDescription = "Localized description",
                                    Modifier.size(AssistChipDefaults.IconSize))
                            }



                    }
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                )

                ElevatedAssistChip(
                    onClick = { /* Do something! */ },
                    label = { Text("Инструкция по получению Token") },
                    leadingIcon = {
                        Icon(
                           painterResource(id = R.drawable.smile) ,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize)
                        )
                    }
                )

            }

        }
    }


}



@Composable
fun hideClearIcon(boolean: Boolean, isClearing: () -> Unit): Unit {
    return if (boolean) {
        IconButton(onClick = { isClearing() }
        )
        {
            Icon(Icons.Default.Clear, contentDescription = null)
        }
    } else {

    }
}

