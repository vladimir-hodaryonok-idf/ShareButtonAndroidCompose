package com.idf_flutter_labsharebutton

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.idf_flutter_labsharebutton.ui.theme.ShareButtonTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val message = remember { mutableStateOf("") }
            ShareButtonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        TextField(value = message.value, onValueChange = {
                            message.value = it
                        })
                        Button(onClick = {
                            val sendIntent: Intent = Intent()
                                .apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, message.value)
                                    type = "text/plain"
                                }
                            val sharingIntent = Intent.createChooser(sendIntent, "Movie Sharing")
                            startActivity(sharingIntent)
                        }) {
                            Text(text = "Share")
                        }
                    }
                }
            }
        }
    }
}

