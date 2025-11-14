package com.amaurypm.videogamescmjc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.amaurypm.videogamescmjc.ui.screens.gamedetail.GameDetailScreen
import com.amaurypm.videogamescmjc.ui.screens.gameslist.GamesListScreen

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenDestination.GamesListScreenDestination
    ) {

        composable<ScreenDestination.GamesListScreenDestination>{
            //Aquí va el código de la primera pantalla
            GamesListScreen(
                navController = navController
            )
        }

        composable<ScreenDestination.GameDetailScreenDestination> { backStackEntry ->
            //Código para la pantalla de detalles
            val args = backStackEntry.toRoute<ScreenDestination.GameDetailScreenDestination>()

            GameDetailScreen(
                gameId = args.gameId
            )
        }

    }

}