package com.example.android_task.ui.rv

import androidx.recyclerview.widget.RecyclerView
import com.example.android_task.R
import com.example.android_task.databinding.FilmListItemBinding
import com.example.android_task.model.simple.Film
import com.squareup.picasso.Picasso


class FilmViewHolder(val binding: FilmListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: Film) {

        with(binding) {
            Picasso.get().load(item.poster?.url).fit().centerInside()
                .error(R.drawable.card_carousel_error).into(ivPic)

            tvFirstName.text = item!!.name
            tvId.text = item.id.toString()

        }
    }


}