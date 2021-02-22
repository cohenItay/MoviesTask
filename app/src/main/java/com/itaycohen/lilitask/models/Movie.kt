package com.itaycohen.lilitask.models

data class Movie (
    val id: Int? = null,
    val title: String? = null,
    val year: String? = null,
    val runtime: String? = null,
    val genres : List<String>? = null,
    val director: String? = null,
    val actors: String? = null,
    val plot: String? = null,
    val posterUrl: String? = null
)
