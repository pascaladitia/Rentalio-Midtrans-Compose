package com.pascal.rentalio.ui.screen.payment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midtrans.sdk.uikit.api.model.CustomColorTheme
import com.midtrans.sdk.uikit.external.UiKitApi
import com.pascal.rentalio.domain.model.ResponseHistory
import com.pascal.rentalio.domain.base.UiState
import com.pascal.rentalio.domain.usecase.HistoryUC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val historyUC: HistoryUC
) : ViewModel() {

    fun initPayment(context: Context) {
        UiKitApi.Builder()
            .withContext(context)
            .withMerchantUrl("https://snap-merchant-server.herokuapp.com/api/")
            .withMerchantClientKey("SB-Mid-client-hOWJXiCCDRvT0RGr")
            .enableLog(true)
            .withColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
            .build()
        uiKitCustomSetting()
    }

    private fun uiKitCustomSetting() {
        val uIKitCustomSetting = UiKitApi.getDefaultInstance().uiKitSetting
        uIKitCustomSetting.saveCardChecked = true
    }
}