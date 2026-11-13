package com.amaurypm.videogamescmjc.data.remote.model

import com.google.gson.annotations.SerializedName

class GameDetailDto(
    @SerializedName("title")
    var title: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("long_desc")
    var longDesc: String
)
