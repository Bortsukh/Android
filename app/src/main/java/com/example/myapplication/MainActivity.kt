package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.FilmDetailsActivity.Companion.FILM_DETAILS_COMMENTS
import com.example.myapplication.FilmDetailsActivity.Companion.FILM_DETAILS_LIKE

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setButtonHandler(R.id.details_film_1, R.id.name_film_1, R.string.harry_potter_description, R.drawable.harry_potter)
        setButtonHandler(R.id.details_film_2, R.id.name_film_2, R.string.back_to_future_description, R.drawable.back_to_the_future)
        setButtonHandler(R.id.details_film_3, R.id.name_film_3, R.string.star_wars_description, R.drawable.star_wars)
        inviteFriend()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(FILM_NAME_1_TEXT_COLORS, findViewById<TextView>(R.id.name_film_1).currentTextColor)
        outState.putInt(FILM_NAME_2_TEXT_COLORS, findViewById<TextView>(R.id.name_film_2).currentTextColor)
        outState.putInt(FILM_NAME_3_TEXT_COLORS, findViewById<TextView>(R.id.name_film_3).currentTextColor)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        var film_name_1_color = savedInstanceState.getInt(FILM_NAME_1_TEXT_COLORS)
        findViewById<TextView>(R.id.name_film_1).setTextColor(film_name_1_color)
        var film_name_2_color = savedInstanceState.getInt(FILM_NAME_2_TEXT_COLORS)
        findViewById<TextView>(R.id.name_film_2).setTextColor(film_name_2_color)
        var film_name_3_color = savedInstanceState.getInt(FILM_NAME_3_TEXT_COLORS)
        findViewById<TextView>(R.id.name_film_3).setTextColor(film_name_3_color)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                if (data != null) {
                    Log.d("Like", data.getBooleanExtra(FILM_DETAILS_LIKE, false).toString())
                    Log.d("Comment", data.getStringExtra(FILM_DETAILS_COMMENTS).toString())
                }
            }
        }
    }

    private fun setButtonHandler(buttonId: Int, textViewId: Int, detail: Int, imageId: Int) {
        findViewById<Button>(buttonId).setOnClickListener {
            findViewById<TextView>(textViewId).setTextColor(Color.RED)
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra(FILM_DETAILS, getString(detail))
            intent.putExtra(FILM_IMAGE, imageId)
            startActivityForResult(intent, REQUEST_CODE)
        }
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