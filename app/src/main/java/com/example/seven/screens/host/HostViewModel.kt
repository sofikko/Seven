package com.example.seven.screens.host

import android.content.Context
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.seven.data.AlertActions
import com.example.seven.data.Screen
import com.example.seven.screens.second.removeLocalPhotos

data class AlertInfo(
    var label: String?,
    var text: String?,
    var confirmButton: Pair<String, () -> Unit>,
    var dismissButton: Pair<String, () -> Unit>,
    var onDismissButton: ()->Unit
) {}

class HostViewModel(val context: Context): ViewModel() {
    @OptIn(ExperimentalMaterial3Api::class)
    var drawerState: MutableState<DrawerValue> = mutableStateOf(DrawerValue.Closed)
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.FirstScreen)
    var alertInfo: MutableState<AlertInfo> = mutableStateOf(AlertInfo("","",Pair("") {},Pair("") {}) {})

    val alertMap: Map<AlertActions, AlertInfo> = mapOf(
        Pair(
            AlertActions.Delete,
            AlertInfo(
                label = "Удаление фото",
                text = "Вы уверены что хотите удалить все снимки? Это действие необратимо!",
                confirmButton = Pair("Да") {
                    currentScreen.value = Screen.SecondScreen
                    removeLocalPhotos(context)
                },
                dismissButton = Pair("Нет") {
                    currentScreen.value = Screen.SecondScreen
                },
                onDismissButton = {
                    currentScreen.value = Screen.SecondScreen
                }
            )
        )
    )
}