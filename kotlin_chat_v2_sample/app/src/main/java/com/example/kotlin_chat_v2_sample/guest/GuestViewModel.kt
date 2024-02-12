package com.example.kotlin_chat_v2_sample.guest

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.kustomer.core.models.KusInitialMessage
import com.kustomer.core.models.KusPreferredView
import com.kustomer.core.models.KusResult
import com.kustomer.core.models.chat.KusChatMessageDirection
import com.kustomer.core.models.chat.KusConversation
import com.kustomer.ui.Kustomer

class GuestViewModel : ViewModel() {

    val unreadCount = liveData {
        emitSource(Kustomer.getInstance().observeUnreadCount())
    }

    val activeConversationIds = liveData {
        emitSource(Kustomer.getInstance().observeActiveConversationIds())
    }

    private val _snackbarEvent = MutableLiveData<String?>()
    val snackbarEvent
        get() = _snackbarEvent

    private fun showSnackbar(message: String) {
        _snackbarEvent.value = message
    }

    fun snackbarComplete() {
        _snackbarEvent.value = null
    }

    fun openSdk() {
        Kustomer.getInstance().open()
    }

    fun openNewChat() {
        val initialMessage = KusInitialMessage("Hey there! How can I help you?", KusChatMessageDirection.AGENT)
        Kustomer.getInstance()
            .startNewConversation(initialMessage) { result: KusResult<KusConversation> ->
                when (result) {
                    is KusResult.Success -> {
                        showSnackbar("New conversation created")
                    }
                    is KusResult.Error -> showSnackbar("New conversation creation error")
                    else -> {}
                }
            }
    }

    fun openChatOnly() {
        Kustomer.getInstance().open(KusPreferredView.CHAT_ONLY)
    }

    fun openKbOnly() {
        Kustomer.getInstance().open(KusPreferredView.KB_ONLY)
    }

    fun toggleDarkMode(isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class GuestViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (GuestViewModel() as T)
}
