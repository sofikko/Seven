package com.example.seven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.seven.screens.first.FirstScreenViewModel
import com.example.seven.screens.first.FirstScreenViewModelFactory
import com.example.seven.screens.host.HostViewModel
import com.example.seven.screens.host.HostViewModelFactory
import com.example.seven.screens.host.StartHost
import com.example.seven.screens.second.SecondScreenViewModel
import com.example.seven.screens.second.SecondScreenViewModelFactory
import com.example.seven.ui.theme.MyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hostViewModel = ViewModelProvider(this, HostViewModelFactory(this))
            .get(HostViewModel::class.java)

        val firstScreenViewModel = ViewModelProvider(this, FirstScreenViewModelFactory(this))
            .get(FirstScreenViewModel::class.java)

        val secondScreenViewModel = ViewModelProvider(this, SecondScreenViewModelFactory(this))
            .get(SecondScreenViewModel::class.java)
        
        setContent {
            MyTheme {
                StartHost(
                    hostViewModel,
                    firstScreenViewModel,
                    secondScreenViewModel
                )
            }
        }
    }
}



