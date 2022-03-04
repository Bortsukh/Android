package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class DetailsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val details = arguments?.getString(MainFragment.FILM_DETAILS)
        val image = arguments?.getInt(MainFragment.FILM_IMAGE)
        view.findViewById<TextView>(R.id.details_text).text = details
        view.findViewById<ImageView>(R.id.image_details).setImageResource(image!!)
        return view
    }
}