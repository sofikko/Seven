package com.example.seven.screens.first

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FirstScreenViewModel(val context: Context): ViewModel() {
    var url: MutableState<String> = mutableStateOf("")
}