package com.groot.quotify.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groot.quotify.ui.module.home.HomeScreen
import com.groot.quotify.ui.module.saved.SavedQuoteList


@Composable
fun NavHostController(navController: NavHostController, isDarkTheme: MutableState<Boolean>) {
    NavHost(navController = navController, startDestination = Route.homeScreen) {
        composable(Route.splashScreen) {

        }

        composable(Route.homeScreen) {
            HomeScreen(navController, isDarkTheme)
        }


        composable(Route.savedQuoteListScreen) {
            SavedQuoteList(navController, isDarkTheme)
        }
    }

}
