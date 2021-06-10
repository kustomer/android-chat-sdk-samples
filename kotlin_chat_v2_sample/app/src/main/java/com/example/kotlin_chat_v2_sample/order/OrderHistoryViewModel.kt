package com.example.kotlin_chat_v2_sample.order

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
            val conversationId = orderToConversationMap[orderNumber]

            // If we've opened a conversation for this order before, open the existing conversation
            if (conversationId != null) {
                Kustomer.getInstance().openConversationWithId(conversationId) {
                    if (it is KusResult.Success) {
                        showSnackbar("Open conversation success")
                    } else {
                        showSnackbar("Open conversation error")
                    }
                }
            } else {
                // If this is the first time chatting about this order, open a new conversation
                Kustomer.getInstance().openNewConversation { conversationResult ->
                    Log.d("openNew", conversationResult.dataOrNull?.id ?: "")
                    if (conversationResult is KusResult.Success) {
                        orderToConversationMap[orderNumber] = conversationResult.data.id

                        viewModelScope.launch {

                            // Once the conversation is created successfully, we describe the conversation
                            // with the order number
                            //
                            // *NOTE* this call to describe will fail if you do not have a Klass orderID
                            // of type Text defined in your org. You can change this key and value to better
                            // fit your implementation
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
            // Describe the customer with their email address
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

            // Deregister on logout to ensure that push notifications aren't sent if user isn't authenticated
            Kustomer.getInstance().deregisterDeviceForPushNotifications()

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
