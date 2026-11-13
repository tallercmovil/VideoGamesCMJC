package com.amaurypm.videogamescmjc.data.remote

import com.amaurypm.videogamescmjc.data.remote.model.GameDetailDto
import com.amaurypm.videogamescmjc.data.remote.model.GameDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApi {
    @GET("cm/games/games_list.php")
    suspend fun getGames(): List<GameDto>

    @GET("cm/games/game_detail.php")
    suspend fun getGameDetail(
        @Query("id")
        id: String
    ): GameDetailDto
}