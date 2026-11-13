package com.amaurypm.videogamescmjc.data.remote

import com.amaurypm.videogamescmjc.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val gamesApi = retrofit.create(GamesApi::class.java)
}