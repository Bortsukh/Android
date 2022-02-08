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
        //initRecycler()
        favoritesFilms()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame_layout, MainFragment())
            .commit()
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
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame_layout, FavoritesFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        const val FILM_DETAILS = "FILM_NAME"
        const val FILM_IMAGE = "FILM_IMAGE"
        val favoriteList = mutableSetOf<RecyclerItem>()
        var mainRecyclerAdapter : RecyclerAdapter? = null
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