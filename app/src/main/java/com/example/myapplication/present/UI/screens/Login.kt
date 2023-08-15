package com.example.myapplication.present.UI.screens

import android.util.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import com.example.myapplication.present.theme.*
import timber.log.*

@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun Login(/*viewModel: MyViewModel*/) {

    var tokenText by remember { mutableStateOf("") }
    var pathText by remember { mutableStateOf("") }
    var tokenIconDelete by remember { mutableStateOf(false) }
    var pathIconDelete by remember { mutableStateOf(false) }

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
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = tokenText,
                    onValueChange = {tokenText = it},
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

