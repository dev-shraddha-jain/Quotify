package com.groot.quotify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavHostController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.splashScreen) {
        composable(Route.splashScreen) {


        }

        composable(Route.homeScreen) {


        }
    }

}