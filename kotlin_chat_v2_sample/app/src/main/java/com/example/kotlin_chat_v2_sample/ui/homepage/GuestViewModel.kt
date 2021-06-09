package com.example.kotlin_chat_v2_sample.ui.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.kotlin_chat_v2_sample.ui.homepage.itemviews.BlankItem
import com.example.kotlin_chat_v2_sample.ui.homepage.itemviews.DarkModeItem
import com.example.kotlin_chat_v2_sample.ui.homepage.itemviews.HeaderItem
import com.example.kotlin_chat_v2_sample.utils.HomepageData
import com.kustomer.core.models.KusResult
import com.kustomer.core.models.KusWidgetType
import com.kustomer.core.models.chat.KusConversation
import com.kustomer.ui.Kustomer

class GuestViewModel : ViewModel() {

    val homepageList = listOf(
        HomepageData.DEFAULT_WIDGET,
        HomepageData.NEW_CHAT,
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

    private val _snackbarEvent = MutableLiveData<String?>()
    val snackbarEvent
        get() = _snackbarEvent

    private fun showSnackbar(message: String) {
        _snackbarEvent.value = message
    }

    fun snackbarComplete() {
        _snackbarEvent.value = null
    }

    fun handleClick(option: HomepageData) {
        when (option) {
            HomepageData.DEFAULT_WIDGET -> Kustomer.getInstance().open()
            HomepageData.NEW_CHAT -> Kustomer.getInstance()
                .openNewConversation { result: KusResult<KusConversation> ->
                    when (result) {
                        is KusResult.Success -> {
                            showSnackbar("New conversation created")
                        }
                        is KusResult.Error -> showSnackbar("New conversation creation error")
                    }
                }
            HomepageData.CHAT_ONLY -> Kustomer.getInstance().open(KusWidgetType.CHAT_ONLY)
            HomepageData.KB_ONLY -> Kustomer.getInstance().open(KusWidgetType.KB_ONLY)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class GuestViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (GuestViewModel() as T)
}
