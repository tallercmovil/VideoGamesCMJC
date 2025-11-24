package com.amaurypm.videogamescmjc.ui.screens.gameslist

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.amaurypm.videogamescmjc.data.remote.RetrofitHelper.gamesApi
import com.amaurypm.videogamescmjc.data.remote.model.Game
import com.amaurypm.videogamescmjc.ui.components.CircularProgressBar
import com.amaurypm.videogamescmjc.ui.theme.BackgroundColor
import com.amaurypm.videogamescmjc.utils.Constants
import kotlinx.coroutines.launch

@Composable
fun GamesListScreen(
    navController: NavController
){
    //Se instancia un alcance de corrutinas
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    var games by remember{
        mutableStateOf<List<Game>>(mutableListOf())
    }

    var showProgressBar by remember{
        mutableStateOf(true)
    }

    fun loadData(){
        scope.launch {
            try{
                games = gamesApi.getGames()

                Log.d(Constants.LOGTAG, "Respuesta: $games")

            }catch (e: Exception){
                //Manejamos la excepción
                Toast.makeText(
                    context,
                    "No hay conexión disponible: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }finally {
                showProgressBar = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadData()
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .background(BackgroundColor)
        ){
            if(showProgressBar)
                CircularProgressBar()

            LazyColumn {
                items(games){game ->
                    GameItem(
                        navController = navController,
                        game = game
                    )
                }
            }
        }
    }


}