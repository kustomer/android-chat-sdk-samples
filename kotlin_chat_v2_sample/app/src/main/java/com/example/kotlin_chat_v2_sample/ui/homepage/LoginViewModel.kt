package com.example.kotlin_chat_v2_sample.ui.homepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kustomer.core.models.KusResult
import com.kustomer.ui.Kustomer
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _navigateToOrderHistory = MutableLiveData<String?>()
    val navigateToOrderHistory
        get() = _navigateToOrderHistory

    private val _navigateToGuestScreen = MutableLiveData<Boolean?>()
    val navigateToGuestScreen
        get() = _navigateToGuestScreen

    private val _snackbarEvent = MutableLiveData<String?>()
    val snackbarEvent
        get() = _snackbarEvent

    fun logIn(email: String) {
        // Handle your app's login process as usual. Here, we'll assume that app login always succeeds and returns true
        // Once app login succeeds, you can continue to log the user in to Kustomer
        val loginIsValid = true

        if (loginIsValid) {
            onLoginSucceeded(email)
        }
    }

    // Normally, JWTs should be generated by your backend and fetched by your app. To test login
    // functionality, replace the jwt value with a valid token generated using your org's secret key.
    // For more information, see this project's README
    private fun onLoginSucceeded(email: String) {
        viewModelScope.launch {
            // TODO: This is an expired JWT for email@kustomer.com. Replace it with a valid JWT for your organization.
            val jwt =
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImVtYWlsQGt1c3RvbWVyLmNvbSIsImlhdCI6MTYyMzI1Nzg2N30.ecGnTE8DT91P0U8jBUCEW1FgsQbrvH4SEXEv5s3dqcM"
            Kustomer.getInstance().logIn(jwt) {
                if (it is KusResult.Success) {
                    showSnackbar("Login success")
                    _navigateToOrderHistory.value = email
                } else {
                    showSnackbar("Login failed")
                    // Continue to order history, though customer will not be logged in to chat
                    _navigateToOrderHistory.value = email
                }
            }
        }
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

    private fun showSnackbar(message: String) {
        _snackbarEvent.value = message
    }

    fun snackbarComplete() {
        _snackbarEvent.value = null
    }
}

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (LoginViewModel() as T)
}
