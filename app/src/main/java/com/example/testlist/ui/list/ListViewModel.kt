package com.example.testlist.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testlist.data.Announcement
import com.example.testlist.data.repositories.NetworkRepository
import com.example.testlist.utils.Left
import com.example.testlist.utils.Right
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ListState(
    val announcements: List<Announcement> = listOf(),
    val loaders: Int = 0,
    val error: Boolean = false
)

class ListViewModel(private val networkRepository: NetworkRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ListState())
    val uiState: StateFlow<ListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch { getData() }
    }

    fun update() {
        changeErrorState(false)
        viewModelScope.launch { getData() }
    }

    private suspend fun getData() {
        updateLoaders(1)
        when (val r = networkRepository.getAnnouncements()) {
            is Right -> {
                updateAnnouncements(r.v)
            }

            is Left -> {
                Log.d("ListViewModel", r.v.message ?: "")
                changeErrorState(true)
            }
        }
        updateLoaders(-1)
    }

    private fun updateAnnouncements(value: List<Announcement>) {
        _uiState.update { state -> state.copy(announcements = value) }
    }

    private fun updateLoaders(value: Int) {
        _uiState.update { state -> state.copy(loaders = state.loaders + value) }
    }

    private fun changeErrorState(value: Boolean) {
        _uiState.update { state -> state.copy(error = value) }
    }

}