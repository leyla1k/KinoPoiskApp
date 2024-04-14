package com.example.android_task.ui.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.android_task.databinding.FilterItemBinding

class FilterViewHolder(val binding: FilterItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: String) {
        with(binding) {
            tvCountry.text = item

        }
    }
}
