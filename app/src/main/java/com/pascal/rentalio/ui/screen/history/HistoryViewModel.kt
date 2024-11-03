package com.pascal.rentalio.ui.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascal.rentalio.domain.model.ResponseHistory
import com.pascal.rentalio.domain.base.UiState
import com.pascal.rentalio.domain.model.Booking
import com.pascal.rentalio.domain.usecase.HistoryUC
import com.pascal.rentalio.domain.usecase.LocalUC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyUC: HistoryUC,
    private val localUC: LocalUC
) : ViewModel() {

    private val _historyState = MutableStateFlow<UiState<ResponseHistory?>>(UiState.Empty)
    val historyState: StateFlow<UiState<ResponseHistory?>> = _historyState

    suspend fun loadHistory() {
        _historyState.value = UiState.Loading

        viewModelScope.launch {
            historyUC.execute().collect {
                try {
                    val list = mutableListOf(Booking())
                    list.clear()
                    list.addAll(it.body()?.records?.bookings ?: emptyList())

                    localUC.getHistory().collect { local ->
                        list.addAll(local)
                    }

                    it.body()?.records?.bookings = list

                    _historyState.value = UiState.Success(it.body())
                } catch (e: Exception) {
                    _historyState.value = UiState.Error(e.message.toString())
                }
            }
        }
    }

    suspend fun addHistory(body: Booking) {
        viewModelScope.launch {
            localUC.addHistory(body)
        }
    }

}