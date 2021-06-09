package com.example.kotlin_chat_v2_sample.utils

enum class HomepageData(val title: String, val description: String) {
    DEFAULT_WIDGET(
        "Open Widget",
        "The widget will open based on your organization's display settings"
    ),
    NEW_CHAT("Open to New Chat", "The Widget will open directly to a new chat thread"),
    CHAT_ONLY(
        "Open as Chat Only",
        "The Widget will open with only the chat screens available regardless of Org settings"
    ),
    KB_ONLY(
        "Open as KB Only",
        "The Widget will open with only the KB screens available regardless of Org settings"
    ),
}

interface HomepageItemListener {
    fun onClick(option: HomepageData)
}
