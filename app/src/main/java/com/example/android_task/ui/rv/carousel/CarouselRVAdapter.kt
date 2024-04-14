package com.example.android_task.ui.rv.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task.R
import com.example.android_task.databinding.ItemCarouselBinding
import com.example.android_task.model.simple.Poster
import com.squareup.picasso.Picasso


class CarouselRVAdapter(private val carouselDataList: MutableList<Poster>) :
    RecyclerView.Adapter<CarouselItemViewHolder>() {

    lateinit var itemCarouselBinding: ItemCarouselBinding

    fun updateData(newData: List<Poster>) {

        carouselDataList.clear() // Очистка старых данных
        carouselDataList.addAll(newData) // Добавление новых данных
        notifyDataSetChanged() // Уведомление адаптера об изменениях
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        itemCarouselBinding =
            ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselItemViewHolder(itemCarouselBinding)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {

        val imageUrl = carouselDataList?.get(position)
        imageUrl?.let { holder.onBind(it) }
        Picasso.get().load(imageUrl?.url).fit().centerInside().error(R.drawable.card_carousel_error)
            .into(itemCarouselBinding.ivPoster)
    }

    override fun getItemCount(): Int {
        return carouselDataList?.size ?: 0
    }

}