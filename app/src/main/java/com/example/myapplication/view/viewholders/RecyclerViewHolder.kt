package com.example.myapplication.view.viewholders

import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.RecyclerItemClickListener

class RecyclerViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
    val imageFilm : ImageView = itemView.findViewById(R.id.image_film_1)
    val nameFilm : TextView = itemView.findViewById(R.id.name_film_1)

    fun bind(item: RecyclerItem, listener: RecyclerItemClickListener) {
//        imageFilm.setImageResource(item.imageFilm)
        Glide.with(imageFilm.context)
            .load(item.imageFilm)
            .placeholder(R.drawable.harry_potter)
            .error(R.drawable.star_wars)
            .into(imageFilm)
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