package com.example.android_task.ui.rv

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.android_task.databinding.FilterItemBinding

class FilterAdapter: RecyclerView.Adapter<FilterViewHolder>() {

    var onFilterClickListener: ((String) -> Unit)? = null
    lateinit var itemFilterBinding: FilterItemBinding

    var data: List<String> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        itemFilterBinding = FilterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterViewHolder(itemFilterBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val item = data[position]
        holder.onBind(item)
        with(holder.binding) {
            containerFilter.setOnClickListener() {
                onFilterClickListener?.invoke(item)
            }
        }

    }


}
