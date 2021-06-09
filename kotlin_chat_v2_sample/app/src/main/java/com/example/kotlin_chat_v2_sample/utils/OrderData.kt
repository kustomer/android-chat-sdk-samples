package com.example.kotlin_chat_v2_sample.utils

import com.example.kotlin_chat_v2_sample.R

class OrderData {

    companion object {
        val orders =
            listOf(
                Order("Order 123", 123, "2 chocolate cakes", R.drawable.ic_baseline_cake_24),
                Order("Order 456", 456, "1 cheese cake", R.drawable.ic_baseline_cake_24),
                Order(
                    "Order 789",
                    789,
                    "1 carrot cake, extra icing",
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
