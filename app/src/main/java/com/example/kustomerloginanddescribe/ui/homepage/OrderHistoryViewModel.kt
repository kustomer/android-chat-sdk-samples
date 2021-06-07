package com.example.kustomerloginanddescribe.ui.homepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kustomerloginanddescribe.utils.JwtGenerator
import com.kustomer.core.models.KusResult
import com.kustomer.core.models.chat.KusCustomerDescribeAttributes
import com.kustomer.core.models.chat.KusEmail
import com.kustomer.ui.Kustomer
import kotlinx.coroutines.launch

class OrderHistoryViewModel : ViewModel() {

    private var orderToConversationMap = mutableMapOf<Int, String>()

    fun openExistingOrNewOrderChat(orderNumber: Int) {
        viewModelScope.launch {
            val convoId = orderToConversationMap[orderNumber]
            if (convoId != null) {
                Kustomer.getInstance().openConversationWithId(convoId) {}
            } else {
                Kustomer.getInstance().openNewConversation {
                    Log.d("openNew", it.dataOrNull?.id ?: "")
                    if (it is KusResult.Success) {
                        orderToConversationMap[orderNumber] = it.data.id

                        viewModelScope.launch {
                            Kustomer.getInstance().describeConversation(
                                it.data.id,
                                mapOf(Pair("orderId", orderNumber))
                            )
                        }
                    }
                }
            }
        }
    }

    fun describeCustomer(email: String) {
        viewModelScope.launch {
            Kustomer.getInstance()
                .describeCustomer(KusCustomerDescribeAttributes(emails = listOf(KusEmail(email))))
        }
    }

    fun logOut() {
        viewModelScope.launch {
            Kustomer.getInstance().logOut()

            // TODO: "log out" of app
        }
    }
}

@Suppress("UNCHECKED_CAST")
class OrderHistoryViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (OrderHistoryViewModel() as T)
}