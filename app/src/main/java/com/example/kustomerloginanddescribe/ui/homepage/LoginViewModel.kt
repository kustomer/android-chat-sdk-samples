package com.example.kustomerloginanddescribe.ui.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModel : ViewModel() {

    private val _navigateToOrderHistory = MutableLiveData<String?>()
    val navigateToOrderHistory
        get() = _navigateToOrderHistory

    private val _navigateToGuestScreen = MutableLiveData<Boolean?>()
    val navigateToGuestScreen
        get() = _navigateToGuestScreen

    fun logIn(email: String, password: String) {
        // Handle your app's login process as usual. Here, we'll assume that app login always succeeds and returns true
        // Once app login succeeds, you can continue to log the user in to Kustomer
        val loginIsValid = true

        if (loginIsValid) {
            onLoginSucceeded(email)
        }
    }

    private fun onLoginSucceeded(email: String) {
        _navigateToOrderHistory.value = email
    }

    fun loginEventComplete() {
        _navigateToOrderHistory.value = null
    }

    fun continueAsGuest() {
        _navigateToGuestScreen.value = true
    }

    fun navigateToGuestScreenComplete() {
        _navigateToGuestScreen.value = null
    }

}

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (LoginViewModel() as T)
}