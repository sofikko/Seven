package com.example.seven.function

import android.content.Context
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seven.data.Screen
import com.example.seven.screens.host.AlertInfo
import com.example.seven.screens.second.removeLocalPhotos
import com.example.seven.ui.theme.PurpleGrey40
import com.example.seven.ui.theme.backgroundColor
import com.example.seven.ui.theme.buttonColor
import com.example.seven.ui.theme.buttonTextColor
import com.example.seven.ui.theme.mainTextColor
import com.example.seven.ui.theme.myTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetTopAppBar(
    label: String,
    action: @Composable ()->Unit = { },
    onClickDrawer: () -> Unit
) {
    TopAppBar(
        title = { Text(label) },
        navigationIcon = {
            IconButton(onClick = onClickDrawer) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        },
        actions = {
            action()
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}


@Composable
fun GetBottomAppBar(listOfButtons: List<Triple<ImageVector, String, () -> Unit>>) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
        containerColor = Color.Blue,
        contentColor = Color.White
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            itemsIndexed(listOfButtons) {
                    _, item ->
                IconButton(
                    modifier = Modifier
                        .padding(16.dp),
                    onClick = item.third) {
                    Icon(imageVector = item.first, contentDescription = null)
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetDrawer(
    drawerState: DrawerState,
    content: @Composable () -> Unit,
    mainContent: @Composable () -> Unit) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet (
                modifier = Modifier
                    .wrapContentSize(Alignment.Center)
            ) {
                content()
            }
        }
    ) {
        mainContent()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetScaffold(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center),
    topAppBar: @Composable () -> Unit = {},
    bottomAppBar: @Composable () -> Unit = {},
    mainContent: @Composable (PaddingValues) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,

        topBar = {
            topAppBar()
        },

        bottomBar = {
            bottomAppBar()
        }

    ) { paddingValues ->
        mainContent(paddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetDrawerContent(
    listOfButtons: List<Triple<ImageVector, String, () -> Unit>>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        itemsIndexed(listOfButtons) {
                _, item ->
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .background(PurpleGrey40),
                onClick = item.third,

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = item.first,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = item.second,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        style = TextStyle(
                            color = mainTextColor,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Normal,
                            background = PurpleGrey40,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }

        }
    }
}

@Composable
fun GetAlert(
    label: String?,
    text: String?,
    onDismissRequest: () -> Unit = {},
    confirmButton: Pair<String, () -> Unit>,
    dismissButton: Pair<String, () -> Unit>
) {
    AlertDialog(
        containerColor = backgroundColor,
        title = { Text(text = label?:"") },
        text = { Text(text = text?:"") },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(onClick =
                confirmButton.second
            ) {
                Text(confirmButton.first)
            }
        },
        dismissButton = {
            Button(onClick = dismissButton.second) {
                Text(text = dismissButton.first)
            }
        }
    )
}











