package com.amaurypm.videogamescmjc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.amaurypm.videogamescmjc.ui.screens.GameDetail
import com.amaurypm.videogamescmjc.ui.screens.GamesList

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenDestination.GamesListScreenDestination
    ) {

        composable<ScreenDestination.GamesListScreenDestination> {
            GamesList(
                navController = navController
            )
        }

        composable<ScreenDestination.GameDetailScreenDestination> {backStackEntry ->

            val args = backStackEntry.toRoute<ScreenDestination.GameDetailScreenDestination>()

            GameDetail(
                gameId = args.id
            )
        }

    }
}