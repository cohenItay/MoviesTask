package com.itaycohen.lilitask.ui.movies.detailed

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialContainerTransform
import com.itaycohen.lilitask.R
import com.itaycohen.lilitask.databinding.FragmentMovieBinding
import com.itaycohen.lilitask.models.Movie
import com.itaycohen.lilitask.ui.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val moviesViewModel: MoviesViewModel by hiltNavGraphViewModels (R.id.moviesCollectionFragment)
    private val args: MovieDetailFragmentArgs by navArgs()
    private var _binding: FragmentMovieBinding? = null
    private val binding: FragmentMovieBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        check (args.movieId > 0) { "Movie id: ${args.movieId} must be valid" }
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel.activeMovieLiveData.observe(viewLifecycleOwner, activeMovieObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val activeMovieObserver = Observer { movie: Movie? ->
        movie ?: return@Observer
        bindToMovieInfo(movie)
    }

    @SuppressLint("SetTextI18n")
    private fun bindToMovieInfo(movie: Movie) = with (binding) {
        requireActivity().findViewById<MaterialToolbar>(R.id.topAppBar)?.title = movie.title
        Glide.with(backgroundImageView)
            .load(movie.posterUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(false)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(backgroundImageView)
        plotText.text = "${movie.plot}, ${movie.year}"
        directorTextView.text = getString(R.string.directed_by, movie.director)
        actorsTextView.text = getString(R.string.actors, movie.actors)
    }
}