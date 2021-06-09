package com.example.kustomerloginanddescribe.ui.homepage.itemviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kustomerloginanddescribe.R
import com.example.kustomerloginanddescribe.databinding.ItemBlankBinding
import com.kustomer.ui.adapters.KusItemView

class BlankItemView :
    KusItemView<BlankItem, BlankItemViewHolder>(
        BlankItem::class.java
    ) {

    override fun createViewHolder(parent: ViewGroup): BlankItemViewHolder {
        return BlankItemViewHolder.from(
            parent
        )
    }

    override fun bindViewHolder(
        model: BlankItem,
        viewHolder: BlankItemViewHolder
    ) {
        viewHolder.bind()
    }

    override fun getFeedItemType() = R.layout.item_blank

    override fun areItemsTheSame(
        oldItem: BlankItem,
        newItem: BlankItem
    ): Boolean {
        return oldItem.show == newItem.show
    }

    override fun areContentsTheSame(
        oldItem: BlankItem,
        newItem: BlankItem
    ) = oldItem == newItem
}

class BlankItemViewHolder private constructor(binding: ItemBlankBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): BlankItemViewHolder {
            val binding = ItemBlankBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return BlankItemViewHolder(
                binding
            )
        }
    }

    fun bind() {}
}

data class BlankItem(val show: Boolean? = true)
