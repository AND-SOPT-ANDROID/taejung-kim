package org.sopt.and.presentation.mypage

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.ServicePool
import org.sopt.and.data.dto.ResponseMyHobbyDto
import org.sopt.and.presentation.home.MovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel: ViewModel() {
    private val myService by lazy { ServicePool.myService }
    val myWatching: StateFlow<List<MovieData>> field = MutableStateFlow(emptyList())

    private val _myInterest = MutableStateFlow<List<MovieData>>(emptyList())
    val myInterest: StateFlow<List<MovieData>> = _myInterest

    private val _hobbyState = mutableStateOf<String?>(null)
    val hobbyState: State<String?> get() = _hobbyState

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

    fun getUserHobby(){
        viewModelScope.launch {
            myService.getMyHobby().enqueue(object:
                Callback<ResponseMyHobbyDto> {
                override fun onResponse(
                    call: Call<ResponseMyHobbyDto>,
                    response: Response<ResponseMyHobbyDto>
                ) {
                    if (response.isSuccessful) {
                        val hobbyResponse = response.body()
                        _hobbyState.value = hobbyResponse?.result?.hobby
                    }
                    else {
                        val error = response.message()
                        Log.e("error", error.toString())
                    }
                }

                override fun onFailure(call: Call<ResponseMyHobbyDto>, t: Throwable) {
                    Log.e("failure", t.message.toString())
                }

            })
        }
    }

}