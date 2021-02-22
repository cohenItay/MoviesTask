package com.itaycohen.lilitask.models

import androidx.recyclerview.widget.DiffUtil

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
) {

    companion object {
        val diffUtilCallback = object: DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }
}
