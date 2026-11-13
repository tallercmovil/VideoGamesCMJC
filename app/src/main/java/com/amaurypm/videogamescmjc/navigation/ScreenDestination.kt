package com.amaurypm.videogamescmjc.navigation

import kotlinx.serialization.Serializable

sealed interface ScreenDestination {

    @Serializable
    object GamesListScreenDestination: ScreenDestination

    @Serializable
    data class GameDetailScreenDestination(
        val id: String
    ): ScreenDestination
}