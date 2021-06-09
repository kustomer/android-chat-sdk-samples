package com.example.kustomerloginanddescribe.utils

import com.example.kustomerloginanddescribe.R

class OrderData {

    companion object {
        val orders =
            listOf(
                Order("Order 123", 123, "2 personal pan pizzas", R.drawable.ic_baseline_cake_24),
                Order("Order 456", 456, "1 large cheese pizza", R.drawable.ic_baseline_cake_24),
                Order(
                    "Order 789",
                    789,
                    "1 medium cheese pizza, 1 Cesar salad",
                    R.drawable.ic_baseline_cake_24
                )
            )
    }
}

data class Order(
    val title: String,
    val orderNumber: Int,
    val description: String,
    val image: Int,
    val conversationId: String? = ""
)
