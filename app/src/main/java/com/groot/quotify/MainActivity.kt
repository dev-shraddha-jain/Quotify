package com.groot.quotify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.groot.quotify.navigation.NavHostController
import com.groot.quotify.ui.theme.QuotifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme = remember { mutableStateOf(false) } // Or load from preferences
            QuotifyTheme(
                content = {
                    val navController = rememberNavController()
                    NavHostController(navController, isDarkTheme)
                },
                isDarkTheme = isDarkTheme
            )
        }
    }
}
