package org.sopt.and.presentation.mypage

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.presentation.home.MovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    val myWatching: StateFlow<List<MovieData>> field = MutableStateFlow(emptyList())

    private val _myInterest = MutableStateFlow<List<MovieData>>(emptyList())
    val myInterest: StateFlow<List<MovieData>> = _myInterest

    private val _hobbyState = MutableStateFlow<HobbyState>(HobbyState.Idle)
    val hobbyState: StateFlow<HobbyState> = _hobbyState

    private val _hobbyData = MutableStateFlow<String?>(null)
    val hobbyData: StateFlow<String?> = _hobbyData

    init {
        loadMovies()
    }

    private fun loadMovies() {
        myWatching.value = listOf(
            MovieData("1", R.drawable.movie1),
            MovieData("2", R.drawable.movie2),
            MovieData("3", R.drawable.movie3),
            MovieData("4", R.drawable.movie4),
            MovieData("5", R.drawable.movie5)
        )
    }

    fun getUserHobby() {
        viewModelScope.launch {
            val result = userRepository.getUserHobby()
            result.fold(
                onSuccess = { userHobby ->
                    _hobbyData.value = userHobby.hobby
                    HobbyState.Success(userHobby.hobby)
                },
                onFailure = {
                    _hobbyState.value =
                        HobbyState.Failure(it.message ?: "")
                }
            )
        }
    }

}