package com.example.testlist.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testlist.data.repositories.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class ListState(
    val loaders: Int = 0
)

class ListViewModel(private val networkRepository: NetworkRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val r = networkRepository.getAnnouncements()
                Log.d("zxc", r.toString())
            }
        }

    }

}