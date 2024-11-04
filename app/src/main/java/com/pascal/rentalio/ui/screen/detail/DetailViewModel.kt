package com.pascal.rentalio.ui.screen.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme
import com.midtrans.sdk.corekit.models.BillingAddress
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ItemDetails
import com.midtrans.sdk.corekit.models.ShippingAddress
import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import com.pascal.rentalio.domain.usecase.HistoryUC

class DetailViewModel(
    private val historyUC: HistoryUC
) : ViewModel() {

    fun initPayment(context: Context) {
        SdkUIFlowBuilder.init()
            .setClientKey("SB-Mid-client-UpdeLV8Hbq4_9iVe")
            .setContext(context)
            .setTransactionFinishedCallback(TransactionFinishedCallback {
                    result ->
                // this is logic
            })
            .setMerchantBaseUrl("https://ayokode.com/midtrans/checkout.php/")
            .enableLog(true)
            .setColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
            .setLanguage("id")
            .buildSDK()
    }

    fun payment(context: Context) {
        val transactionRequest = TransactionRequest("Kita-Store-"+System.currentTimeMillis().toString() + "", 1.0)
        val detail = ItemDetails("NamaItemId", 1000.0, 1, "Sample Payment")
        val itemDetails = ArrayList<com.midtrans.sdk.corekit.models.ItemDetails>()
        itemDetails.add(detail)
        uiKitDetails(transactionRequest)
        transactionRequest.itemDetails = itemDetails
        MidtransSDK.getInstance().transactionRequest = transactionRequest
        MidtransSDK.getInstance().startPaymentUiFlow(context)
    }

    fun uiKitDetails(transactionRequest: TransactionRequest){
        val customerDetails = CustomerDetails()
        customerDetails.customerIdentifier = "Supriyanto"
        customerDetails.phone = "082345678999"
        customerDetails.firstName = "Supri"
        customerDetails.lastName = "Yanto"
        customerDetails.email = "test@gmail.com"
        val shippingAddress = ShippingAddress()
        shippingAddress.address = "Baturan, Gantiwarno"
        shippingAddress.city = "Klaten"
        shippingAddress.postalCode = "51193"
        customerDetails.shippingAddress = shippingAddress
        val billingAddress = BillingAddress()
        billingAddress.address  = "Baturan, Gantiwarno"
        billingAddress.city = "Klaten"
        billingAddress.postalCode = "51193"
        customerDetails.billingAddress = billingAddress
        transactionRequest.customerDetails = customerDetails
    }
}