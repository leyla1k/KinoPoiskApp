package com.example.android_task.ui.rv.review

import androidx.recyclerview.widget.RecyclerView
import com.example.android_task.databinding.FilmListItemBinding
import com.example.android_task.databinding.ItemReviewBinding
import com.example.android_task.model.simple.Film
import com.example.android_task.model.simple.Review


class ReviewViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Review) {
        with(binding) {
            tvTitle.text=item.title
            tvType.text=item.type
            tvReview.text=item.review
        }
    }





}
