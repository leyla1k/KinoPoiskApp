package com.example.android_task.ui.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.android_task.databinding.FilmListItemBinding

import com.example.android_task.model.simple.Film


class FilmAdapter : PagingDataAdapter<Film, FilmViewHolder>(diffCallback) {

    var onFilmClickListener: ((Film) -> Unit)? = null

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            FilmListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item!!)



        with(holder.binding) {
            containerFilm.setOnClickListener() {
                onFilmClickListener?.invoke(item)
            }
        }

    }


}