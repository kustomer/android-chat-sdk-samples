package com.example.kustomerloginanddescribe.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.kustomerloginanddescribe.ui.homepage.itemviews.BlankItem
import com.example.kustomerloginanddescribe.ui.homepage.itemviews.DarkModeItem
import com.example.kustomerloginanddescribe.ui.homepage.itemviews.HeaderItem
import com.example.kustomerloginanddescribe.model.HomepageData
import com.kustomer.ui.Kustomer

class GuestViewModel : ViewModel() {

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
}

@Suppress("UNCHECKED_CAST")
class GuestViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (GuestViewModel() as T)
}