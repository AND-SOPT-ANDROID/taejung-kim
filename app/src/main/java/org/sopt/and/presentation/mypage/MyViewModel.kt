package org.sopt.and.presentation.mypage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.R
import org.sopt.and.presentation.home.MovieData

class MyViewModel: ViewModel() {
    private val _myWatching = MutableStateFlow<List<MovieData>>(emptyList())
    val myWatching: StateFlow<List<MovieData>> = _myWatching

    private val _myInterest = MutableStateFlow<List<MovieData>>(emptyList())
    val myInterest: StateFlow<List<MovieData>> = _myInterest

    init {
        loadMovies()
    }

    private fun loadMovies() {
        _myWatching.value = listOf(
            MovieData("1", R.drawable.movie1),
            MovieData("2", R.drawable.movie2),
            MovieData("3", R.drawable.movie3),
            MovieData("4", R.drawable.movie4),
            MovieData("5", R.drawable.movie5)
        )
    }

}