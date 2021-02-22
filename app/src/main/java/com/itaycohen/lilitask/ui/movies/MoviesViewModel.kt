package com.itaycohen.lilitask.ui.movies

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.itaycohen.lilitask.R
import com.itaycohen.lilitask.models.Movie
import com.itaycohen.lilitask.models.QueryState
import com.itaycohen.lilitask.repositories.movies.MoviesRepository
import com.itaycohen.lilitask.ui.movies.grid.GridItemViewHolder
import com.itaycohen.lilitask.ui.movies.grid.MoviesCollectionFragment
import com.itaycohen.lilitask.ui.movies.grid.MoviesCollectionFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class MoviesViewModel @Inject constructor(
    @ApplicationContext private val appContext: Context, // No worry from memory leak - it is the app context
    private val repository: MoviesRepository
) : ViewModel(), GridItemViewHolder.Listener {

    val moviesLiveData: LiveData<List<Movie>> = MutableLiveData(listOf())
    val moviesQueryStateLiveData : LiveData<QueryState> = MutableLiveData(QueryState.Idle)

    fun refreshMovies() = viewModelScope.launch {
        moviesLiveData as MutableLiveData
        moviesQueryStateLiveData as MutableLiveData
        try {
            moviesQueryStateLiveData.value = QueryState.Running
            moviesLiveData.value = repository.refreshMovies()
            moviesQueryStateLiveData.value = QueryState.Success
        } catch (e: Exception) {
            when (e) {
                is HttpException,
                is IOException -> {
                    moviesQueryStateLiveData.value = QueryState.Failure(appContext.getString(R.string.problem_with_fetching_data))
                }
                else -> throw e
            }
        }
    }

    override fun onItemClick(v: View, bindingAdapterPos: Int) {
        val movie = moviesLiveData.value!![bindingAdapterPos]
        movie.id?.also {
            val action = MoviesCollectionFragmentDirections.actionMoviesCollectionFragmentToMovieFragment(it)
            v.findNavController().navigate(action)
        }
    }
}