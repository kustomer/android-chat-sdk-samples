package com.example.kustomerloginanddescribe.ui.homepage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kustomerloginanddescribe.databinding.FragmentOrderHistoryBinding

class OrderHistoryFragment : Fragment() {
    private lateinit var viewModel: OrderHistoryViewModel
    private lateinit var viewModelFactory: OrderHistoryViewModelFactory

    private var _binding: FragmentOrderHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = OrderHistoryViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(OrderHistoryViewModel::class.java)

        binding.run {
            welcome.text = "Welcome, friend"
            order1.title.text = "Order 1"
            order2.title.text = "Order 2"
            order3.title.text = "Order 3"
        }
    }
}