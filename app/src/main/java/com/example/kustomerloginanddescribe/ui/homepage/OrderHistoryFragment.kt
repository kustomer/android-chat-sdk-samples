package com.example.kustomerloginanddescribe.ui.homepage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.kustomerloginanddescribe.R
import com.example.kustomerloginanddescribe.databinding.FragmentOrderHistoryBinding

class OrderHistoryFragment : Fragment() {
    private lateinit var viewModel: OrderHistoryViewModel
    private lateinit var viewModelFactory: OrderHistoryViewModelFactory

    private var _binding: FragmentOrderHistoryBinding? = null
    private val binding get() = _binding!!

    private val args: OrderHistoryFragmentArgs by navArgs()

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
            welcome.text = "Welcome, ${args.email}"

            order1.title.text = "Order 1"
            order1.description.text = "2 personal pan pizzas"
            order1.image.setImageResource(R.drawable.ic_baseline_cake_24)
            order1.root.setOnClickListener {
                Log.d("Order 1", "clicked")
            }

            order2.title.text = "Order 2"
            order2.description.text = "1 large cheese pizza"
            order2.image.setImageResource(R.drawable.ic_baseline_cake_24)
            order2.root.setOnClickListener {
                Log.d("Order 2", "clicked")
            }


            order3.title.text = "Order 3"
            order3.description.text = "1 medium cheese pizza, 1 Cesar Salad"
            order3.image.setImageResource(R.drawable.ic_baseline_cake_24)
            order3.root.setOnClickListener {
                Log.d("Order 3", "clicked")
            }

        }
    }
}