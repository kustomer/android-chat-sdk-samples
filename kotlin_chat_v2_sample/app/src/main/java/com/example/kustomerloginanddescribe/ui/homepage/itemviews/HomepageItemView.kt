package com.example.kustomerloginanddescribe.ui.homepage.itemviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kustomerloginanddescribe.R
import com.example.kustomerloginanddescribe.databinding.ItemHomepageBinding
import com.example.kustomerloginanddescribe.utils.HomepageData
import com.example.kustomerloginanddescribe.utils.HomepageItemListener
import com.kustomer.ui.adapters.KusItemView

class HomepageItemView(
    private val clickListener: HomepageItemListener
) :
    KusItemView<HomepageData, HomepageItemViewHolder>(
        HomepageData::class.java
    ) {

    override fun createViewHolder(parent: ViewGroup): HomepageItemViewHolder {
        return HomepageItemViewHolder.from(
            parent
        )
    }

    override fun bindViewHolder(
        model: HomepageData,
        viewHolder: HomepageItemViewHolder
    ) {
        viewHolder.bind(
            model,
            clickListener
        )
    }

    override fun getFeedItemType() = R.layout.item_homepage

    override fun areItemsTheSame(oldItem: HomepageData, newItem: HomepageData): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: HomepageData, newItem: HomepageData) =
        oldItem == newItem
}

class HomepageItemViewHolder private constructor(private val binding: ItemHomepageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): HomepageItemViewHolder {
            val binding = ItemHomepageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return HomepageItemViewHolder(
                binding
            )
        }
    }

    fun bind(
        data: HomepageData,
        clickListener: HomepageItemListener
    ) {
        binding.title.text = data.title
        binding.description.text = data.description
        binding.root.setOnClickListener { clickListener.onClick(data) }
    }
}
