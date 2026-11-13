package com.amaurypm.videogamescmjc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenDestination.GamesListScreenDestination
    ) {

        composable<ScreenDestination.GamesListScreenDestination> {

        }

        composable<ScreenDestination.GameDetailScreenDestination> {backStackEntry ->

            val args = backStackEntry.toRoute<ScreenDestination.GameDetailScreenDestination>()



        }

    }
}