package com.amaurypm.videogamescmjc.ui.screens.gameslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.amaurypm.videogamescmjc.data.remote.model.Game
import com.amaurypm.videogamescmjc.navigation.ScreenDestination
import com.amaurypm.videogamescmjc.ui.theme.GameItemColor


@Composable
fun GameItem(
    navController: NavController,
    game: Game
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = GameItemColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                onClick = {
                    //Click de cada juego
                    navController.navigate(
                        ScreenDestination.GameDetailScreenDestination(
                            gameId = game.id
                        )
                    )
                }, //Efecto Ripple
                indication = ripple(
                    color = Color.White,
                    bounded = true
                ),
                interactionSource = remember { MutableInteractionSource() }
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(game.thumbnail),
                contentDescription = "Game Image",

                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(58.dp)
                    .aspectRatio(3f / 5f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = game.title,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Nintendo",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF3FE0DD)
                )
                Text(
                    text = "Released: 2020",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF4A5B82)
                )
                Text(
                    text = "ESRB Rating: E-Everyone",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFf5dc53)
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun GameItemPreview() {
    GameItem(
        navController = rememberNavController(),
        Game(
            id = "251",
            thumbnail = "https://www.serverbpw.com/cm/games/imgs/21357t.png",
            title = "Mario Kart World",
        )
    )
}
