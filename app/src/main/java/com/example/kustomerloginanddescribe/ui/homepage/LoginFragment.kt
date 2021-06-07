package com.example.kustomerloginanddescribe.ui.homepage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kustomerloginanddescribe.R
import com.example.kustomerloginanddescribe.databinding.LoginFragmentBinding
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = LoginViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LoginViewModel::class.java)

        binding.run {
            appTitle.text =
                "${resources.getString(R.string.app_name)} v.${BuildConfig.VERSION_NAME}"

            viewModel.navigateToOrderHistory.observe(viewLifecycleOwner, {
                if (it != null) {
                    val action =
                        LoginFragmentDirections
                            .actionLoginFragmentToOrderHistoryFragment(emailEt.text.toString())
                    findNavController().navigate(action)
                    viewModel.loginEventComplete()
                }
            })

            viewModel.navigateToGuestScreen.observe(viewLifecycleOwner, {
                if (true == it) {
                    val action =
                        LoginFragmentDirections
                            .actionLoginFragmentToGuestFragment()
                    findNavController().navigate(action)
                    viewModel.navigateToGuestScreenComplete()
                }
            })

            logIn.setOnClickListener {
                viewModel.logIn(emailEt.text.toString(), passwordEt.text.toString())
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

/**
 * TODO:
 * - Add license
 * - Add clarifying comments on all calls to Kustomer, links to supporting docs, etc
 * - Do we need better app icon/image assets, etc?
 * - Rewrite history before making public
 * - remove JWT generator -- link to online generator and paste in, include sample JWT token,
 * - Add toasts for success/failures
 *
 * Questions:
 * - Should we demonstrate registering/deregistering for push in this example? Y
 * - Should we convert the Guest view to something simpler than RecyclerView to minimize code?
 * -
* */