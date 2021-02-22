package com.itaycohen.lilitask.models

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("movies")
    val moviesList: List<Movie>? = null
)