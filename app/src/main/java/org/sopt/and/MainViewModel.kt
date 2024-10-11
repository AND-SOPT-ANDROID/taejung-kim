package org.sopt.and

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // LiveData로 구현
    private val _id = MutableLiveData<String?>()
    val id: LiveData<String?> = _id

    // id를 저장하는 함수
    fun setId(newId: String?) {
        Log.d("newId", newId.toString())
        _id.value = newId
    }
}