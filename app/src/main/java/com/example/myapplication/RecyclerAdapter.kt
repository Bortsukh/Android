package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private val values: MutableList<RecyclerItem>,
    private val itemClickListener: RecyclerItemClickListener
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

    fun removeItem(position: Int) {
        values.removeAt(position)
        notifyItemRemoved(position)
    }

    fun changeColorFavorite(item: RecyclerItem) {
        values.asSequence().filter { it.nameFilm == item.nameFilm }.forEach { it.isFavorite = false }
        notifyDataSetChanged()
    }
}
interface RecyclerItemClickListener {
    fun onItemLongClick(recyclerItem: RecyclerItem, position: Int)
    fun onItemShortClick(recyclerItem: RecyclerItem, position: Int)
}