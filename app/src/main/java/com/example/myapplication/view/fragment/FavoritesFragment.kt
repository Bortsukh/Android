package com.example.myapplication.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.view.activity.MainActivity
import com.google.android.material.snackbar.Snackbar

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        return initRecycler(view as RecyclerView)
    }

    fun initRecycler(recyclerView :RecyclerView) : View {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(MainActivity.favoriteList.toMutableList(), object: RecyclerItemClickListener {
            override fun onItemLongClick(recyclerItem: RecyclerItem, position: Int) {
                MainActivity.favoriteList.remove(recyclerItem)
                (recyclerView.adapter as RecyclerAdapter)?.removeItem(position)
                MainActivity.mainRecyclerAdapter?.changeColorFavorite(recyclerItem)
                Snackbar.make(recyclerView,"Удалено из избранного", Snackbar.LENGTH_LONG).show()
            }

            override fun onItemShortClick(recyclerItem: RecyclerItem, position: Int) {
                val fragment = DetailsFragment()
                val arguments = Bundle().apply {
                    putString(MainFragment.FILM_DETAILS, recyclerItem.filmDetails)
                    putString(MainFragment.FILM_IMAGE, recyclerItem.imageFilm) }
                fragment.arguments = arguments
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, fragment).commit()
            }
        })
        return recyclerView
    }
}