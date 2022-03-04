package com.example.myapplication.model.api

import retrofit2.Call
import retrofit2.http.*

interface Api {

        @GET("anime")
        fun getFilmListWithPages(): Call<FilmModel>
}