package com.itaycohen.lilitask.ui.movies.grid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.itaycohen.lilitask.R
import com.itaycohen.lilitask.databinding.FragmentMoviesCollectionBinding
import com.itaycohen.lilitask.models.Movie
import com.itaycohen.lilitask.models.QueryState
import com.itaycohen.lilitask.ui.movies.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviesCollectionFragment : Fragment() {

    private val moviesViewModel: MoviesViewModel by hiltNavGraphViewModels(R.id.moviesCollectionFragment)
    private var _binding: FragmentMoviesCollectionBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel.refreshMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(moviesViewModel) {
            moviesQueryStateLiveData.observe(viewLifecycleOwner, moviesQueryStateObserver)
            moviesLiveData.observe(viewLifecycleOwner, moviesDataObserver)
        }
        with(binding.moviesRecyclerView) {
            adapter = MoviesGridAdapter(moviesViewModel)
            setHasFixedSize(true)
            addItemDecoration(
                MoviesItemDecoration(
                    context,
                    R.dimen.movies_grid_item_offset,
                    2
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val moviesQueryStateObserver = Observer<QueryState> { qs ->
        when (qs) {
            is QueryState.Success,
            is QueryState.Idle -> binding.moviesProgressBar.hide()
            is QueryState.Running -> binding.moviesProgressBar.show()
            is QueryState.Failure -> qs.errMsg?.also { errMsg ->
                binding.moviesProgressBar.hide()
                Snackbar.make(requireView(), errMsg, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private val moviesDataObserver = Observer<List<Movie>> { moviesList ->
        val adapter = binding.moviesRecyclerView.adapter as MoviesGridAdapter
        adapter.submitList(moviesList)
    }
}