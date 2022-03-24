package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.view.viewholders.RecyclerViewHolder

class RecyclerAdapter(
    private val itemClickListener: RecyclerItemClickListener
) : PagedListAdapter<RecyclerItem, RecyclerViewHolder>(FILM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(getItem(position)!!, itemClickListener )
    }

//    override fun getItemCount(): Int {
//        return values.size
//    }
//
//    fun setValues(items: List<RecyclerItem>) {
//        values.clear()
//        values.addAll(items)
//        notifyDataSetChanged()
//    }
//
//    fun removeItem(position: Int) {
//        values.removeAt(position)
//        notifyItemRemoved(position)
//    }
//
//    fun changeColorFavorite(item: RecyclerItem) {
//        values.asSequence().filter { it.nameFilm == item.nameFilm }.forEach { it.isFavorite = false }
//        notifyDataSetChanged()
//    }

    companion object {
        private val FILM_COMPARATOR = object : DiffUtil.ItemCallback<RecyclerItem>() {
            override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean =
                oldItem.nameFilm == newItem.nameFilm
            override fun areContentsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem): Boolean =
                newItem == oldItem
        }
    }

}
interface RecyclerItemClickListener {
    fun onItemLongClick(recyclerItem: RecyclerItem, position: Int)
    fun onItemShortClick(recyclerItem: RecyclerItem, position: Int)
}