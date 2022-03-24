package com.example.myapplication.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecyclerItem(
    val imageFilm: String,
    @PrimaryKey
    val nameFilm : String,
    val filmDetails: String,
    var isFavorite: Boolean
)