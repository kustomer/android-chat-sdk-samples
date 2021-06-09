package com.example.kustomerloginanddescribe.ui.homepage.itemviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kustomerloginanddescribe.R
import com.example.kustomerloginanddescribe.databinding.ItemHeaderBinding
import com.kustomer.ui.adapters.KusItemView

class HeaderItemView :
    KusItemView<HeaderItem, HeaderItemViewHolder>(
        HeaderItem::class.java
    ) {

    override fun createViewHolder(parent: ViewGroup): HeaderItemViewHolder {
        return HeaderItemViewHolder.from(
            parent
        )
    }

    override fun bindViewHolder(
        model: HeaderItem,
        viewHolder: HeaderItemViewHolder
    ) {
        viewHolder.bind(model)
    }

    override fun getFeedItemType() = R.layout.item_header

    override fun areItemsTheSame(
        oldItem: HeaderItem,
        newItem: HeaderItem
    ): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(
        oldItem: HeaderItem,
        newItem: HeaderItem
    ) = oldItem == newItem
}

class HeaderItemViewHolder private constructor(private val binding: ItemHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): HeaderItemViewHolder {
            val binding = ItemHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return HeaderItemViewHolder(
                binding
            )
        }
    }

    fun bind(data: HeaderItem) {
        binding.title.text = data.title
    }
}

data class HeaderItem(val title: String)