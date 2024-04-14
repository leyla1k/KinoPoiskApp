package com.example.android_task.ui.rv.filter

import androidx.recyclerview.widget.RecyclerView
import com.example.android_task.databinding.FilterItemBinding

class FilterViewHolder(val binding: FilterItemBinding) : RecyclerView.ViewHolder(binding.root) {
    var state = false
    fun onBind(item: String) {
        with(binding) {
            tvCountry.text = item

        }
    }
}
