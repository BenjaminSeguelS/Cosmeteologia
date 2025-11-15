package com.example.cosmeteologia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cosmeteologia.navigation.AppNavigation
import com.example.cosmeteologia.ui.theme.CosmeteologiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CosmeteologiaTheme {
                AppNavigation()
            }
        }
    }
}
