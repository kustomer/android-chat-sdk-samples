package com.example.kustomerloginanddescribe.ui.homepage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kustomer.core.models.KusResult
import com.kustomer.core.models.chat.KusCustomerDescribeAttributes
import com.kustomer.core.models.chat.KusEmail
import com.kustomer.ui.Kustomer
import kotlinx.coroutines.launch

class OrderHistoryViewModel : ViewModel() {

    private var orderToConversationMap = mutableMapOf<Int, String>()

    private val _snackbarEvent = MutableLiveData<String?>()
    val snackbarEvent
        get() = _snackbarEvent

    fun openExistingOrNewOrderChat(orderNumber: Int) {
        viewModelScope.launch {
            val convoId = orderToConversationMap[orderNumber]
            if (convoId != null) {
                Kustomer.getInstance().openConversationWithId(convoId) {
                    if (it is KusResult.Success) {
                        showSnackbar("Open conversation sucess")
                    } else {
                        showSnackbar("Open conversation error")
                    }
                }
            } else {
                Kustomer.getInstance().openNewConversation { conversationResult ->
                    Log.d("openNew", conversationResult.dataOrNull?.id ?: "")
                    if (conversationResult is KusResult.Success) {
                        orderToConversationMap[orderNumber] = conversationResult.data.id

                        viewModelScope.launch {
                            Kustomer.getInstance().describeConversation(
                                conversationResult.data.id,
                                mapOf(Pair("orderId", orderNumber))
                            ) {
                                if (it is KusResult.Success) {
                                    showSnackbar("Describe conversation success")
                                } else {
                                    showSnackbar("Describe conversation error")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun describeCustomer(email: String) {
        viewModelScope.launch {
            Kustomer.getInstance()
                .describeCustomer(KusCustomerDescribeAttributes(emails = listOf(KusEmail(email)))) {
                    if (it is KusResult.Success) {
                        showSnackbar("Describe customer success")
                    } else {
                        showSnackbar("Describe customer error")
                    }
                }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            Kustomer.getInstance().logOut()

            // Handle your app's normal logout functionality
        }
    }

    private fun showSnackbar(message: String) {
        _snackbarEvent.value = message
    }

    fun snackbarComplete() {
        _snackbarEvent.value = null
    }
}

@Suppress("UNCHECKED_CAST")
class OrderHistoryViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (OrderHistoryViewModel() as T)
}