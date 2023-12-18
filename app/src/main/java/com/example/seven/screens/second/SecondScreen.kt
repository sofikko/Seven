package com.example.seven.screens.second

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import java.io.File

@Composable
fun SecondScreen(paddingValues: PaddingValues, viewModel: SecondScreenViewModel) {
    val localPhotos = loadLocalPhotos(viewModel.context)

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        itemsIndexed(localPhotos) { _, photoFile ->
            val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
            val imageBitmap = remember { bitmap.asImageBitmap() }
            PhotoItem(photo = imageBitmap)
        }
    }
}

@Composable
fun PhotoItem(photo: ImageBitmap) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .aspectRatio(1f)
    ) {
        Image(
            bitmap = photo,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}

private fun loadLocalPhotos(context: Context): List<File> {
    val directory = context.filesDir
    val files = directory.listFiles()
    return files?.toList() ?: emptyList()
}

fun removeLocalPhotos(context: Context) {
    for (elem in context.filesDir.listFiles()
        ?.toList()!!) elem.delete()
}

