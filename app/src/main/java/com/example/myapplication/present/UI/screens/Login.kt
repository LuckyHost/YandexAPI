package com.example.myapplication.present.UI.screens

import android.util.*
import android.widget.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import com.example.myapplication.R
import com.example.myapplication.domain.retrofit.DataClass.*
import com.example.myapplication.present.*
import com.example.myapplication.present.theme.*
import kotlinx.coroutines.*
import retrofit2.*
import timber.log.*

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavHostController, mainViewModel: MainViewModel) {

    var tokenText by remember { mutableStateOf("") }
    var pathText by remember { mutableStateOf("") }
    var isErrorToken by remember { mutableStateOf(false) }
    var isErrorPath by remember { mutableStateOf(false) }
    var isLoadFile by remember { mutableStateOf(false) }
    var codeError by remember { mutableStateOf(0) }
    val context = LocalContext.current

    Log.d("MyLog",tokenText.toString())
    Log.d("MyLog",pathText)



    LaunchedEffect(isLoadFile){

      if (isLoadFile) {
          delay(500)
          navController.popBackStack()
          navController.navigate("Home")
      }
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
//                    placeholder = { Text("by Makarov.D") },
                    leadingIcon = {
                        Icon(Icons.Outlined.Lock, contentDescription = null)
                    },
                    trailingIcon = {
                        hideClearIcon(!tokenText.isEmpty()) { tokenText = "" }
                    },
                    isError = isErrorToken


                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(text = "Работа\\Отчеты\\Потери\\Статистика" )} ,
                    value = pathText,
                    onValueChange = { pathText = it },
                    label = { Text("Путь к папке на Yandex.Disk") },
                    singleLine = true,
                    leadingIcon = {
                        Icon(Icons.Outlined.Info, contentDescription = null)
                    },
                    trailingIcon = {
                        hideClearIcon(!pathText.isEmpty()) { pathText = "" }
                    },
                    isError = isErrorPath,
                    enabled = !tokenText.isEmpty()
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )


                ElevatedAssistChip(
                    colors = if (isLoadFile) {
                        AssistChipDefaults.elevatedAssistChipColors(containerColor= Green)
                    }
                            else{AssistChipDefaults.elevatedAssistChipColors()},
                    onClick = {
                        Log.d("MyLog","Click")
                        isErrorToken=false
                        isErrorPath=false
                        mainViewModel.insertTokenAndPath(tokenText,pathText)
                        mainViewModel.startLoadingFile(){
                            codeError=it
                            when (codeError) {
                                200 -> {isLoadFile=true}
                                401 -> {isErrorToken=true ; Toast.makeText(context, "Неверный Token",Toast.LENGTH_SHORT ).show()}
                                404 -> {isErrorPath=true;Toast.makeText(context, "Не найдет такой путь на вашем Yandex.Disk",Toast.LENGTH_SHORT ).show()}
                            }
                            Log.d("MyLog"," Ошибка Retrofit $it")
                        }

                              },
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

