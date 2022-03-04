package com.example.myapplication.model.db

data class RecyclerItem(
    val imageFilm: String, val nameFilm : String, val filmDetails: String, var isFavorite: Boolean
)