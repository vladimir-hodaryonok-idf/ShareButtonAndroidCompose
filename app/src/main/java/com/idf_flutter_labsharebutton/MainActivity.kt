package com.idf_flutter_labsharebutton

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                        Button(modifier = Modifier.padding(all = 10.dp), onClick = {
                            shareText(message.value)
                        }) {
                            Text(text = "Share Text")
                        }
                        Button(modifier = Modifier.padding(all = 10.dp),
                            onClick = {
                                shareImage.launch("image/*")
                            }
                        ) {
                            Text(text = "Share Image")
                        }
                    }
                }
            }
        }
    }

    private val shareImage = registerForActivityResult(GetContent()) { uri: Uri? ->
        val sendIntent: Intent = Intent()
            .apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uri)
                type = "image/jpeg"
            }
        val sharingIntent = Intent.createChooser(sendIntent, "Movie Sharing")
        startActivity(sharingIntent)
    }

    private fun shareText(message: String) {
        val sendIntent: Intent = Intent()
            .apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }
        val sharingIntent = Intent.createChooser(sendIntent, "Movie Sharing")
        startActivity(sharingIntent)
    }

}

