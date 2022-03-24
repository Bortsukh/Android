package com.example.myapplication.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.R

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
        val image = arguments?.getString(MainFragment.FILM_IMAGE)
        view.findViewById<TextView>(R.id.details_text).text = details
        val im = view.findViewById<ImageView>(R.id.image_details)
        Glide.with(im.context)
            .load(image)
            .placeholder(R.drawable.harry_potter)
            .error(R.drawable.star_wars)
            .into(im)
        return view
    }
}