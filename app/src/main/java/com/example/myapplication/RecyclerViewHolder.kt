package com.example.myapplication

import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val imageFilm : ImageView = itemView.findViewById(R.id.image_film_1)
    val nameFilm : TextView = itemView.findViewById(R.id.name_film_1)

    fun bind(item: RecyclerItem, listener: RecyclerItemClickListener) {
        imageFilm.setImageResource(item.imageFilm)
        nameFilm.text = item.nameFilm

        if(item.isFavorite) {
            nameFilm.setTextColor(RED)
        } else nameFilm.setTextColor(BLACK)

        itemView.setOnLongClickListener {
            if(item.isFavorite) {
            nameFilm.setTextColor(RED)
            }
            listener.onItemLongClick(item, adapterPosition)
            true
        }

        itemView.setOnClickListener{
            listener.onItemShortClick(item, adapterPosition)
        }
    }
}