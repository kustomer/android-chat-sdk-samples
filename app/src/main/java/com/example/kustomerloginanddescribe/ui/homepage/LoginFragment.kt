package com.example.kustomerloginanddescribe.ui.homepage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kustomerloginanddescribe.R
import com.example.kustomerloginanddescribe.databinding.LoginFragmentBinding
import com.kustomer.core.BuildConfig

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = LoginViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        binding.run {
            appTitle.text =
                "${resources.getString(R.string.app_name)} v.${BuildConfig.VERSION_NAME}"

            viewModel.navigateToOrderHistory.observe(viewLifecycleOwner, {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, GuestFragment())
                    .commitNow()
            })

            viewModel.navigateToGuestScreen.observe(viewLifecycleOwner, {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, GuestFragment())
                    .commitNow()
            })

            logIn.setOnClickListener {
                viewModel.logIn(emailEt.text.toString(), passwordEt.text.toString())
            }

            logInAsGuest.setOnClickListener { viewModel.continueAsGuest() }
        }
    }
}