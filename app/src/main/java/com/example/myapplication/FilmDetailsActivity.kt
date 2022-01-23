package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class FilmDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_details)

        val details = intent.extras?.getString(MainActivity.FILM_DETAILS)
        findViewById<TextView>(R.id.details_text).setText(details)

        val imageId = intent.extras?.getInt(MainActivity.FILM_IMAGE)
        imageId?.let { findViewById<ImageView>(R.id.image_details).setImageResource(it) }

    }

    override fun onBackPressed() {
        val result = intent.apply {
            putExtra(FILM_DETAILS_LIKE, findViewById<CheckBox>(R.id.details_like).isChecked)
            putExtra(FILM_DETAILS_COMMENTS, findViewById<EditText>(R.id.details_comment).text.toString())
        }
        setResult(Activity.RESULT_OK, result)
        finish()
        super.onBackPressed()
    }

    companion object{
        const val FILM_DETAILS_LIKE = "film_details_like"
        const val FILM_DETAILS_COMMENTS = "film_details_comment"
    }
}