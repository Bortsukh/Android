package com.example.myapplication

import android.graphics.Color.RED
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val imageFilm : ImageView = itemView.findViewById(R.id.image_film_1)
    val nameFilm : TextView = itemView.findViewById(R.id.name_film_1)

    fun bind(item: RecyclerItem, listener: FavoriteClickListener) {
        imageFilm.setImageResource(item.imageFilm)
        nameFilm.text = item.nameFilm

        itemView.setOnClickListener {
            nameFilm.setBackgroundColor(RED)
            listener.onFavoriteClick(item, 0)
        }
    }
}