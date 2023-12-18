package com.example.seven.screens.first

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.FileOutputStream
import com.example.seven.ui.theme.Gray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun FirstScreen(paddingValues: PaddingValues, viewModel: FirstScreenViewModel) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .wrapContentSize(Alignment.Center)
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Загрузите ваше фото!",
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            Text("URL: ", fontSize = 18.sp)
            BasicTextField(
                value = viewModel.url.value,
                onValueChange = { viewModel.url.value = it },
                textStyle = TextStyle(fontSize = 18.sp),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(0.8f)
                    .background(Gray)
            )
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    val url = viewModel.url.value

                    try {
                        val image = loadImageFromNetwork(url)
                        async {
                            saveImageToInternalStorage(image, viewModel.context)
                            (viewModel.context as Activity).runOnUiThread {
                                Toast.makeText(
                                    viewModel.context,
                                    "Image loaded!",
                                    Toast.LENGTH_LONG
                                ).show()
                                viewModel.url.value = ""
                            }
                        }
                    } catch (e: Exception) {
                        (viewModel.context as Activity).runOnUiThread {
                            Toast.makeText(
                                viewModel.context,
                                "Error: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        Log.e("MyTag", "Load from URL error: ${e.message}")
                    }

                }
            }
        ) {
            Text("Загрузить")
        }
        Text(
            text = "ИКБО-06-21",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp)
        )
        Text(
            text = "Гришина С. А.",
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        )

    }
}

private fun loadImageFromNetwork(imageUrl: String): Bitmap {
    val url = URL(imageUrl)
    url.openStream().use { input ->
        return Bitmap.createBitmap(BitmapFactory.decodeStream(input))
    }
}

private fun saveImageToInternalStorage(bitmap: Bitmap, context: Context) {
    val timeStamp = SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "image_$timeStamp.jpg"
    val stream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    stream.close()
}