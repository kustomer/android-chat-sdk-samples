package com.example.kotlin_chat_v2_sample.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.kotlin_chat_v2_sample.CustomApplication
import com.example.kotlin_chat_v2_sample.R
import com.example.kotlin_chat_v2_sample.databinding.FragmentGuestBinding
import com.example.kotlin_chat_v2_sample.ui.homepage.itemviews.BlankItemView
import com.example.kotlin_chat_v2_sample.utils.HomepageData
import com.example.kotlin_chat_v2_sample.utils.HomepageItemListener
import com.example.kotlin_chat_v2_sample.ui.homepage.itemviews.DarkModeItemView
import com.example.kotlin_chat_v2_sample.ui.homepage.itemviews.HeaderItemView
import com.example.kotlin_chat_v2_sample.ui.homepage.itemviews.HomepageItemView
import com.google.android.material.snackbar.Snackbar
import com.kustomer.core.BuildConfig
import com.kustomer.ui.adapters.KusAdapter

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = GuestViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(GuestViewModel::class.java)
        application = requireActivity().application as CustomApplication

        setupAdapter()
        binding.run {
            appTitle.text = resources.getString(R.string.app_name, BuildConfig.VERSION_NAME)

            viewModel.unreadCount.observe(viewLifecycleOwner, {
                unreadCount.text = getString(R.string.unread_count, it)
            })

            viewModel.activeConversationIds.observe(viewLifecycleOwner, {
                activeConversationCount.text = getString(R.string.active_conversations, it.size)
            })

            viewModel.snackbarEvent.observe(viewLifecycleOwner, {
                if (it != null) {
                    Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
                    viewModel.snackbarComplete()
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        binding.run {
            val adapter = KusAdapter.createInstance(
                HomepageItemView(homepageItemListener),
                BlankItemView(),
                DarkModeItemView(),
                HeaderItemView()
            )
            rvOptions.adapter = adapter
            rvOptions.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )

            adapter.submitList(viewModel.homepageList)
        }
    }

    private val homepageItemListener = object : HomepageItemListener {
        override fun onClick(option: HomepageData) {
            viewModel.handleClick(option)
        }
    }
}
