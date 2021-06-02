package com.example.kustomerloginanddescribe.ui.homepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.kustomerloginanddescribe.ui.homepage.itemviews.BlankItem
import com.example.kustomerloginanddescribe.ui.homepage.itemviews.DarkModeItem
import com.example.kustomerloginanddescribe.ui.homepage.itemviews.HeaderItem
import com.example.kustomerloginanddescribe.model.HomepageData
import com.example.kustomerloginanddescribe.utils.JwtGenerator
import com.kustomer.core.models.KusResult
import com.kustomer.core.models.chat.KusCustomerDescribeAttributes
import com.kustomer.core.models.chat.KusPhone
import com.kustomer.ui.Kustomer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomepageViewModel : ViewModel() {

    val homepageList = listOf(
        HomepageData.DEFAULT_WIDGET,
        HomepageData.NEW_CHAT,
        HomepageData.OPEN_CONVERSATION_WITH_ID,
        BlankItem(),
        HeaderItem("Overrides"),
        HomepageData.CHAT_ONLY,
        HomepageData.KB_ONLY,
        BlankItem(),
        DarkModeItem(),
    )

    val unreadCount = liveData {
        emitSource(Kustomer.getInstance().observeUnreadCount())
    }

    val activeConversationIds = liveData {
        emitSource(Kustomer.getInstance().observeActiveConversationIds())
    }

    fun openConversationWithId(conversationId: String) {
        viewModelScope.launch {
            Kustomer.getInstance().openConversationWithId(conversationId) {
                when (it) {
                    is KusResult.Error -> Log.e(
                        "HomepageFragment", "New conversation create failed ${it.exception}"
                    )
                    is KusResult.Success -> Log.d(
                        "HomepageFragment", "New conversation created with id ${it.data.id}"
                    )
                }
            }
        }
    }

    fun logIn() {
        viewModelScope.launch {
            loginToMyApp()
            // Call Kustomer.logIn() immediately after the user logs in to your app the first time.
            // Do not call logIn every time the app loads because this requires you to generate a new
            // JWT on each app load.

            // TODO: add example of tracking logged in state

            val jwt = JwtGenerator.getJwt()
            Kustomer.getInstance().logIn(jwt) {
                when (it) {
                    is KusResult.Error -> Log.e(
                        "HomepageFragment", "Customer login failed ${it.exception}"
                    )
                    is KusResult.Success -> Log.d(
                        "HomepageFragment",
                        "Customer logged in with externalId ${it.data.externalId}"
                    )
                }
            }
        }
    }

    fun logOut() {
        // Call Kustomer.logOut to log the customer out from chat and remove all conversations from device
        Kustomer.getInstance().logOut()
        // TODO: deregister device
    }

    fun describeCustomer() {
        // TODO: use describeCustomer
        viewModelScope.launch {
            val customerAttributes =
                KusCustomerDescribeAttributes(phones = listOf(KusPhone("555-555-5555")))
            Kustomer.getInstance().describeCustomer(customerAttributes)
        }
    }

    fun describeConversation() {
        // TODO: Implement describe conversation
    }

    private fun loginToMyApp() {
        // Your app's login logic
    }
}

@Suppress("UNCHECKED_CAST")
class HomepageViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (HomepageViewModel() as T)
}