package com.example.kotlin_chat_v2_sample.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kotlin_chat_v2_sample.R
import com.example.kotlin_chat_v2_sample.databinding.LoginFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.kustomer.core.BuildConfig

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        viewModelFactory = LoginViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        binding.run {
            appTitle.text = resources.getString(R.string.app_name, BuildConfig.VERSION_NAME)

            viewModel.navigateToOrderHistory.observe(viewLifecycleOwner, {
                if (it != null) {
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToOrderHistoryFragment(
                            emailEt.text.toString()
                        )
                    findNavController().navigate(action)
                    viewModel.loginEventComplete()
                }
            })

            viewModel.navigateToGuestScreen.observe(viewLifecycleOwner, {
                if (true == it) {
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToGuestFragment()
                    findNavController().navigate(action)
                    viewModel.navigateToGuestScreenComplete()
                }
            })

            viewModel.snackbarEvent.observe(viewLifecycleOwner, {
                if (it != null) {
                    Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
                    viewModel.snackbarComplete()
                }
            })

            logIn.setOnClickListener {
                viewModel.logIn(emailEt.text.toString())
            }

            logInAsGuest.setOnClickListener { viewModel.continueAsGuest() }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.emailEt.text.clear()
        binding.passwordEt.text.clear()
    }
}
