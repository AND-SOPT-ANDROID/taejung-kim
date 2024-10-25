package org.sopt.and.presentation.main

import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.presentation.home.MovieData

class MainViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<MovieData>>(emptyList())
    val movies: StateFlow<List<MovieData>> = _movies

    private val _categories = MutableStateFlow<List<Int>>(emptyList())
    val categories: StateFlow<List<Int>> = _categories

    init {
        loadMovies()
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _categories.value = listOf(
                R.string.home_category_new_classic,
                R.string.home_category_drama,
                R.string.home_category_entertain,
                R.string.home_category_movie,
                R.string.home_category_animation,
                R.string.home_cateogry_foreign,
                R.string.home_category_normal,
                R.string.home_category_kids
            )
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _movies.value = listOf(
                MovieData("1", R.drawable.movie1),
                MovieData("2", R.drawable.movie2),
                MovieData("3", R.drawable.movie3),
                MovieData("4", R.drawable.movie4),
                MovieData("5", R.drawable.movie5)
            )
        }
    }
}