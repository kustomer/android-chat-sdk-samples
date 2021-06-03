package com.example.kustomerloginanddescribe.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModel : ViewModel() {
    fun logIn(email: String, password: String) {
        // Handle your app's login process as usual.
        // Once your login succeeds, you can continue to log the user in to Kustomer
        val loginIsValid = true

        if (loginIsValid) {
            onLoginSucceeded(email)
        }
    }

    fun continueAsGuest() {
        // Navigate to logged out state
    }

    private fun onLoginSucceeded(email: String) {
        // Log in to Kustomer and navigate to order screen
    }
}

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (LoginViewModel() as T)
}