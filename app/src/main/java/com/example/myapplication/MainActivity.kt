package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.FilmDetailsActivity.Companion.FILM_DETAILS_COMMENTS
import com.example.myapplication.FilmDetailsActivity.Companion.FILM_DETAILS_LIKE

class MainActivity : AppCompatActivity() {
    val favoriteList = mutableListOf<RecyclerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inviteFriend()
        initRecycler()
    }

    fun initRecycler() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(getList(), object: FavoriteClickListener{
            override fun onFavoriteClick(recyclerItem: RecyclerItem, position: Int) {
              favoriteList.add(recyclerItem)
                Log.d("1234567", favoriteList.size.toString())
            }

        })
    }

    fun getList() : List<RecyclerItem> {
        val list = mutableListOf<RecyclerItem>()
        list.add(RecyclerItem(R.drawable.harry_potter, getString(R.string.harry_potter)))
        list.add(RecyclerItem(R.drawable.star_wars, getString(R.string.star_wars)))
        list.add(RecyclerItem(R.drawable.back_to_the_future, getString(R.string.back_to_future)))
        list.add(RecyclerItem(R.drawable.harry_potter, getString(R.string.harry_potter)))
        list.add(RecyclerItem(R.drawable.star_wars, getString(R.string.star_wars)))
        list.add(RecyclerItem(R.drawable.back_to_the_future, getString(R.string.back_to_future)))
        list.add(RecyclerItem(R.drawable.harry_potter, getString(R.string.harry_potter)))
        list.add(RecyclerItem(R.drawable.star_wars, getString(R.string.star_wars)))
        list.add(RecyclerItem(R.drawable.back_to_the_future, getString(R.string.back_to_future)))
        return list
    }

    private fun inviteFriend() {
        findViewById<Button>(R.id.invite).setOnClickListener {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Invite in the Best App.")
        intent.putExtra(Intent.EXTRA_TEXT, "Hi Friend. Open the Best App.")
        intent.setData(Uri.parse("mailto:"))
            startActivity(intent)
        }
    }

    companion object {
        const val FILM_DETAILS = "FILM_NAME"
        const val FILM_IMAGE = "FILM_IMAGE"
        const val FILM_NAME_1_TEXT_COLORS = "film_name_1_text_colors"
        const val FILM_NAME_2_TEXT_COLORS = "film_name_2_text_colors"
        const val FILM_NAME_3_TEXT_COLORS = "film_name_3_text_colors"
        const val REQUEST_CODE = 200
    }
}