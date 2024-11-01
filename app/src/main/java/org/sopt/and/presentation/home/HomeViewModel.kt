package org.sopt.and.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.R

class HomeViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<MovieData>>(emptyList())
    val movies: StateFlow<List<MovieData>> = _movies

    private val _categories = MutableStateFlow<List<Int>>(emptyList())
    val categories: StateFlow<List<Int>> = _categories

    init {
        loadMovies()
        loadCategories()
    }

    private fun loadCategories() {
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

    private fun loadMovies() {
        _movies.value = listOf(
            MovieData("1", R.drawable.movie1),
            MovieData("2", R.drawable.movie2),
            MovieData("3", R.drawable.movie3),
            MovieData("4", R.drawable.movie4),
            MovieData("5", R.drawable.movie5)
        )

    }
}