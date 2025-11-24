package com.amaurypm.videogamescmjc.ui.screens.gamedetail

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.amaurypm.videogamescmjc.data.remote.RetrofitHelper.gamesApi
import com.amaurypm.videogamescmjc.data.remote.model.GameDetail
import com.amaurypm.videogamescmjc.ui.components.CircularProgressBar
import com.amaurypm.videogamescmjc.ui.theme.BackgroundColor
import com.amaurypm.videogamescmjc.utils.Constants
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun GameDetailScreen(
    gameId: String
){
    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    var showProgressBar by remember{
        mutableStateOf(true)
    }

    var gameDetail by remember{
        mutableStateOf(GameDetail("", "", ""))
    }

    fun loadData(){
        scope.launch {
            try{

                gameDetail = gamesApi.getGameDetail(gameId)

                Log.d(Constants.LOGTAG, "Nombre del juego recibido: ${gameDetail.title}")

            }catch (e: Exception){
                Toast.makeText(
                    context,
                    "Conexión no disponible: ${e.message}",
                    Toast.LENGTH_SHORT
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

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ){
                Text(
                    text = gameDetail.title,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(16.dp))
                Image(
                    painter = rememberAsyncImagePainter(gameDetail.image),
                    contentDescription = "Game Image",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = gameDetail.longDesc,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}