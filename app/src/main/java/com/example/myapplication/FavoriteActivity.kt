package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        initRecycler()
    }

    fun initRecycler() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(MainActivity.favoriteList.toMutableList(), object: RecyclerItemClickListener{
            override fun onItemLongClick(recyclerItem: RecyclerItem, position: Int) {
                MainActivity.favoriteList.remove(recyclerItem)
                (recyclerView.adapter as RecyclerAdapter)?.removeItem(position)
                MainActivity.mainRecyclerAdapter?.changeColorFavorite(recyclerItem)
            }

            override fun onItemShortClick(recyclerItem: RecyclerItem, position: Int) {
                val intent = Intent(this@FavoriteActivity, FilmDetailsActivity::class.java)
                intent.putExtra(MainActivity.FILM_DETAILS, recyclerItem.filmDetails)
                intent.putExtra(MainActivity.FILM_IMAGE, recyclerItem.imageFilm)
                startActivity(intent)
            }
        })
    }
}