package com.example.kustomerloginanddescribe.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OrderHistoryViewModel : ViewModel() {
    // TODO: Implement the ViewModel
}

@Suppress("UNCHECKED_CAST")
class OrderHistoryViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (OrderHistoryViewModel() as T)
}