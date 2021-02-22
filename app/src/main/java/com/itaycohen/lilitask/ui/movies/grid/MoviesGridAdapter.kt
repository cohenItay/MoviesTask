package com.itaycohen.lilitask.ui.movies.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.itaycohen.lilitask.databinding.MovieGridItemBinding
import com.itaycohen.lilitask.models.Movie
import com.itaycohen.lilitask.ui.movies.MoviesViewModel

class MoviesGridAdapter(
    private val moviesViewModel: MoviesViewModel
) : ListAdapter<Movie, GridItemViewHolder>(Movie.diffUtilCallback) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)
        val binding = MovieGridItemBinding.inflate(inflater, parent, false)
        return GridItemViewHolder(binding, moviesViewModel)
    }

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
