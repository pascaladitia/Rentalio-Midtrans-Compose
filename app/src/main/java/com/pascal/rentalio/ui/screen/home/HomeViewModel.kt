package com.pascal.rentalio.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.rentalio.domain.model.ResponseHistory
import com.pascal.rentalio.domain.base.UiState
import com.pascal.rentalio.domain.usecase.HistoryUC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val historyUC: HistoryUC
) : ViewModel() {

    private val _historyState = MutableStateFlow<UiState<ResponseHistory?>>(UiState.Empty)
    val historyState: StateFlow<UiState<ResponseHistory?>> = _historyState

    suspend fun loadHistory(token: String) {
        _historyState.value = UiState.Loading

        viewModelScope.launch {
            historyUC.execute().collect {
                try {
                    _historyState.value = UiState.Success(it.body())
                } catch (e: Exception) {
                    _historyState.value = UiState.Error(e.message.toString())
                }
            }
        }
    }
}