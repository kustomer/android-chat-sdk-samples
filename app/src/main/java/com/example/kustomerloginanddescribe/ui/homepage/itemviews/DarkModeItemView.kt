package com.example.kustomerloginanddescribe.ui.homepage.itemviews

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.example.kustomerloginanddescribe.R
import com.example.kustomerloginanddescribe.databinding.ItemDarkModeBinding
import com.kustomer.ui.adapters.KusItemView

class DarkModeItemView :
    KusItemView<DarkModeItem, DarkModeItemViewHolder>(
        DarkModeItem::class.java
    ) {

    override fun createViewHolder(parent: ViewGroup): DarkModeItemViewHolder {
        return DarkModeItemViewHolder.from(
            parent
        )
    }

    override fun bindViewHolder(
        model: DarkModeItem,
        viewHolder: DarkModeItemViewHolder
    ) {
        viewHolder.bind(model)
    }

    override fun getFeedItemType() = R.layout.item_dark_mode

    override fun areItemsTheSame(
        oldItem: DarkModeItem,
        newItem: DarkModeItem
    ): Boolean {
        return oldItem.isEnabled == newItem.isEnabled
    }

    override fun areContentsTheSame(
        oldItem: DarkModeItem,
        newItem: DarkModeItem
    ) = oldItem == newItem
}

class DarkModeItemViewHolder private constructor(val binding: ItemDarkModeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): DarkModeItemViewHolder {
            val binding = ItemDarkModeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return DarkModeItemViewHolder(
                binding
            )
        }
    }

    fun bind(data: DarkModeItem) {

        val nightModeFlags =
            itemView.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
            binding.switchDarkMode.isChecked = true
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}

data class DarkModeItem(val isEnabled: Boolean = true)