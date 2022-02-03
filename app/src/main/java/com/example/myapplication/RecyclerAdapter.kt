package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private val values: List<RecyclerItem>,
    private val itemClickListener: FavoriteClickListener
    ) : RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(values[position], itemClickListener )
    }

    override fun getItemCount(): Int {
        return values.size
    }
}
interface FavoriteClickListener {
    fun onFavoriteClick(recyclerItem: RecyclerItem, position: Int)
}