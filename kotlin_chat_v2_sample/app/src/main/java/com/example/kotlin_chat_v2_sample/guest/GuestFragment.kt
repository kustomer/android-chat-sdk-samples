package com.example.kotlin_chat_v2_sample.guest

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_chat_v2_sample.CustomApplication
import com.example.kotlin_chat_v2_sample.R
import com.example.kotlin_chat_v2_sample.databinding.FragmentGuestBinding
import com.google.android.material.snackbar.Snackbar
import com.kustomer.core.BuildConfig

class GuestFragment : Fragment() {

    private lateinit var viewModel: GuestViewModel
    private lateinit var viewModelFactory: GuestViewModelFactory

    private var _binding: FragmentGuestBinding? = null
    private val binding get() = _binding!!

    private lateinit var application: CustomApplication

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()

        viewModelFactory = GuestViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(GuestViewModel::class.java)
        application = requireActivity().application as CustomApplication

        binding.run {
            appTitle.text = resources.getString(R.string.app_name, BuildConfig.VERSION_NAME)

            viewModel.unreadCount.observe(viewLifecycleOwner, {
                unreadCount.text = getString(R.string.unread_count, it ?: 0)
            })

            viewModel.activeConversationIds.observe(viewLifecycleOwner, {
                activeConversationCount.text = getString(R.string.active_conversations, it.size ?: 0)
            })

            viewModel.snackbarEvent.observe(viewLifecycleOwner, {
                if (it != null) {
                    Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
                    viewModel.snackbarComplete()
                }
            })
        }
    }

    fun setupClickListeners() {

        binding.btnOpenSdk.setOnClickListener {
            viewModel.openSdk()
        }

        binding.btnNewChat.setOnClickListener {
            viewModel.openNewChat()
        }

        binding.btnOpenChat.setOnClickListener {
            viewModel.openChatOnly()
        }

        binding.btnOpenKb.setOnClickListener {
            viewModel.openKbOnly()
        }

        val nightModeFlags =
            requireContext().resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            binding.switchDarkMode.isChecked = true
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleDarkMode(isChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
