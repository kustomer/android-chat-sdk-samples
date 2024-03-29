package com.example.kotlin_chat_v2_sample.order

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlin_chat_v2_sample.R
import com.example.kotlin_chat_v2_sample.databinding.FragmentOrderHistoryBinding
import com.example.kotlin_chat_v2_sample.utils.OrderData
import com.google.android.material.snackbar.Snackbar

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = OrderHistoryViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(OrderHistoryViewModel::class.java)

        viewModel.describeCustomer(args.email)

        viewModel.snackbarEvent.observe(viewLifecycleOwner, {
            if (it != null) {
                Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
                viewModel.snackbarComplete()
            }
        })

        binding.run {
            welcome.text = getString(R.string.welcome, args.email)

            logOutButton.setOnClickListener {
                viewModel.logOut()
                findNavController().popBackStack()
            }

            val firstOrder = OrderData.orders[0]
            order1.title.text = firstOrder.title
            order1.description.text = firstOrder.description
            order1.image.setImageResource(firstOrder.image)
            order1.helpButton.setOnClickListener {
                viewModel.openExistingOrNewOrderChat(firstOrder.orderNumber)
            }

            val secondOrder = OrderData.orders[1]
            order2.title.text = secondOrder.title
            order2.description.text = secondOrder.description
            order2.image.setImageResource(secondOrder.image)
            order2.helpButton.setOnClickListener {
                viewModel.openExistingOrNewOrderChat(firstOrder.orderNumber)
            }

            val thirdOrder = OrderData.orders[2]
            order3.title.text = thirdOrder.title
            order3.description.text = thirdOrder.description
            order3.image.setImageResource(thirdOrder.image)
            order3.helpButton.setOnClickListener {
                viewModel.openExistingOrNewOrderChat(firstOrder.orderNumber)
            }
        }
    }
}
