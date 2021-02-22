package com.itaycohen.lilitask.ui.movies.grid

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.itaycohen.lilitask.R
import com.itaycohen.lilitask.databinding.MovieGridItemBinding
import com.itaycohen.lilitask.models.Movie

class GridItemViewHolder(
    val gridItemBinding: MovieGridItemBinding,
    private val interactionListener: Listener
) : RecyclerView.ViewHolder(gridItemBinding.root) {

    init {
        gridItemBinding.movieImageView.setOnClickListener {
            interactionListener.onItemClick(it as ImageView, bindingAdapterPosition)
        }
    }

    fun bind(movie: Movie) = with (gridItemBinding) {
        nameTextView.text = movie.title
        yearTextView.text = movie.year
        Glide.with(movieImageView)
            .load(movie.posterUrl)
            .transition(DrawableTransitionOptions.withCrossFade(200))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(false)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(movieImageView)
    }

    fun interface Listener {
        fun onItemClick(v: ImageView, bindingAdapterPos: Int)
    }
}
