package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inviteFriend()
        initRecycler()
        favoritesFilms()
    }

    fun initRecycler() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(getList() as MutableList<RecyclerItem>, object: RecyclerItemClickListener{
            override fun onItemLongClick(recyclerItem: RecyclerItem, position: Int) {
                recyclerItem.isFavorite = true
              favoriteList.add(recyclerItem)
                Log.d("1234567", favoriteList.size.toString())
            }

            override fun onItemShortClick(recyclerItem: RecyclerItem, position: Int) {
                val intent = Intent(this@MainActivity, FilmDetailsActivity::class.java)
                intent.putExtra(FILM_DETAILS, recyclerItem.filmDetails)
                intent.putExtra(FILM_IMAGE, recyclerItem.imageFilm)
                startActivity(intent)
            }

        })
        mainRecyclerAdapter = recyclerView.adapter as RecyclerAdapter

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.black_line_5dp, theme)
            ?.let { divider.setDrawable(it) }
        recyclerView.addItemDecoration(divider)
    }

    fun getList() : List<RecyclerItem> {
        val list = mutableListOf<RecyclerItem>()
        list.add(RecyclerItem(R.drawable.harry_potter, getString(R.string.harry_potter), getString(R.string.harry_potter_description),false))
        list.add(RecyclerItem(R.drawable.star_wars, getString(R.string.star_wars), getString(R.string.star_wars_description), false))
        list.add(RecyclerItem(R.drawable.back_to_the_future, getString(R.string.back_to_future), getString(R.string.back_to_future_description),false))
        list.add(RecyclerItem(R.drawable.harry_potter, getString(R.string.harry_potter), getString(R.string.harry_potter_description),false))
        list.add(RecyclerItem(R.drawable.star_wars, getString(R.string.star_wars), getString(R.string.star_wars_description),false))
        list.add(RecyclerItem(R.drawable.back_to_the_future, getString(R.string.back_to_future), getString(R.string.back_to_future_description),false))
        list.add(RecyclerItem(R.drawable.harry_potter, getString(R.string.harry_potter), getString(R.string.harry_potter_description),false))
        list.add(RecyclerItem(R.drawable.star_wars, getString(R.string.star_wars), getString(R.string.star_wars_description),false))
        list.add(RecyclerItem(R.drawable.back_to_the_future, getString(R.string.back_to_future), getString(R.string.back_to_future_description),false))
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

    private fun favoritesFilms() {
        findViewById<Button>(R.id.favorite).setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val FILM_DETAILS = "FILM_NAME"
        const val FILM_IMAGE = "FILM_IMAGE"
        val favoriteList = mutableSetOf<RecyclerItem>()
        var mainRecyclerAdapter : RecyclerAdapter? = null
        const val FILM_NAME_1_TEXT_COLORS = "film_name_1_text_colors"
        const val FILM_NAME_2_TEXT_COLORS = "film_name_2_text_colors"
        const val FILM_NAME_3_TEXT_COLORS = "film_name_3_text_colors"
        const val REQUEST_CODE = 200
    }

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this);
        // set title
        alertDialogBuilder.setTitle("Выход из приложения");
        // set dialog message
        alertDialogBuilder
            .setMessage("Хотите выйти?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, which ->
            super.onBackPressed()
        }
        alertDialogBuilder.show()
    }
}