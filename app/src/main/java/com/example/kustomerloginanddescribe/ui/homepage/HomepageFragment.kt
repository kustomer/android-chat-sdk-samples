package com.example.kustomerloginanddescribe.ui.homepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.kustomerloginanddescribe.KustomerApplication
import com.example.kustomerloginanddescribe.R
import com.example.kustomerloginanddescribe.databinding.FragmentHomepageBinding
import com.example.kustomerloginanddescribe.ui.homepage.itemviews.BlankItemView
import com.example.kustomerloginanddescribe.model.HomepageData
import com.example.kustomerloginanddescribe.model.HomepageItemListener
import com.example.kustomerloginanddescribe.ui.homepage.itemviews.DarkModeItemView
import com.example.kustomerloginanddescribe.ui.homepage.itemviews.HeaderItemView
import com.example.kustomerloginanddescribe.ui.homepage.itemviews.HomepageItemView
import com.kustomer.core.BuildConfig
import com.kustomer.core.models.KusResult
import com.kustomer.core.models.KusWidgetType
import com.kustomer.core.models.chat.KusConversation
import com.kustomer.ui.Kustomer
import com.kustomer.ui.adapters.KusAdapter

class HomepageFragment : Fragment() {

    private lateinit var viewModel: HomepageViewModel
    private lateinit var viewModelFactory: HomepageViewModelFactory

    private var _binding: FragmentHomepageBinding? = null
    private val binding get() = _binding!!

    private lateinit var application: KustomerApplication

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = HomepageViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomepageViewModel::class.java)
        application = requireActivity().application as KustomerApplication

        setupAdapter()
        binding.run {
            // TODO: register device
            //Kustomer.getInstance().registerDevice()

            sdkTitle.text =
                "${resources.getString(R.string.app_name)} : ${BuildConfig.VERSION_NAME}"

            viewModel.unreadCount.observe(viewLifecycleOwner, {
                unreadCount.text = "$it Unread count"
            })

            viewModel.activeConversationIds.observe(viewLifecycleOwner, {
                activeConversationCount.text = "${it.size} Active conversations"
            })

            logIn.setOnClickListener {
                viewModel.logIn()
            }

            logOut.setOnClickListener {
                viewModel.logOut()
            }
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

            when (option) {
                HomepageData.DEFAULT_WIDGET -> Kustomer.getInstance().open()
                HomepageData.NEW_CHAT -> Kustomer.getInstance()
                    .openNewConversation { result: KusResult<KusConversation> ->
                        when (result) {
                            is KusResult.Success -> {
                                Log.d("HomepageFragment", "New conversation created successfully")
                                Kustomer.getInstance().describeConversation(
                                    result.data.id, mapOf("orderIdStr" to result.data.id)
                                ) {
                                    when (it) {
                                        is KusResult.Success ->
                                            Log.d(
                                                "HomepageFragment",
                                                "Described new conversation successfully"
                                            )
                                        else -> Log.d(
                                            "HomepageFragment", "New conversation describe failed"
                                        )
                                    }
                                }
                            }
                            is KusResult.Error -> Log.e(
                                "HomepageFragment",
                                "New conversation create failed ${result.exception}"
                            )
                        }
                    }
                HomepageData.CHAT_ONLY -> Kustomer.getInstance().open(KusWidgetType.CHAT_ONLY)
                HomepageData.KB_ONLY -> Kustomer.getInstance().open(KusWidgetType.KB_ONLY)
            }
        }
    }
}