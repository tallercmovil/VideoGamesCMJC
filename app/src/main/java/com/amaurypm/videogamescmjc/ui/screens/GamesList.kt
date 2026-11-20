package com.amaurypm.videogamescmjc.ui.screens

import android.provider.SyncStateContract
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.amaurypm.videogamescmjc.data.remote.RetrofitHelper.gamesApi
import com.amaurypm.videogamescmjc.data.remote.model.GameDto
import com.amaurypm.videogamescmjc.ui.components.CircularProgressBar
import com.amaurypm.videogamescmjc.ui.components.GameItem
import com.amaurypm.videogamescmjc.ui.theme.BackgroundColor
import com.amaurypm.videogamescmjc.utils.Constants
import kotlinx.coroutines.launch

@Composable
fun GamesList(
    navController: NavController
){

    val scope = rememberCoroutineScope()

    var games by remember{
        mutableStateOf<List<GameDto>>(mutableListOf())
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
                //Procesamos la excepción
            }finally {
                showProgressBar = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadData()
    }

    Scaffold{ innerPadding ->
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