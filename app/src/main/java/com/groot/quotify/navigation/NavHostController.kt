package com.groot.quotify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.groot.quotify.ui.module.home.HomeScreen


@Composable
fun NavHostController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.homeScreen) {
        composable(Route.splashScreen) {

        }

        composable(Route.homeScreen) {
            HomeScreen(navController)
        }
    }

}