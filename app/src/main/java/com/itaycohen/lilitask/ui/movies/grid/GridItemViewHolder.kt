package com.itaycohen.lilitask.ui.movies.grid

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.itaycohen.lilitask.R
import com.itaycohen.lilitask.databinding.MovieGridItemBinding
import com.itaycohen.lilitask.models.Movie

class GridItemViewHolder(
    private val gridItemBinding: MovieGridItemBinding,
    private val interactionListener: Listener
) : RecyclerView.ViewHolder(gridItemBinding.root) {

    init {
        gridItemBinding.root.setOnClickListener {
            interactionListener.onItemClick(it, bindingAdapterPosition)
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
        fun onItemClick(v: View, bindingAdapterPos: Int)
    }
}
