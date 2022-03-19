package com.example.myapplication.model.api

import retrofit2.Call
import retrofit2.http.*

interface Api {

        @GET("anime")
        fun getFilmListWithPages(@Query("page[limit]") items: Int,
                                 @Query("page[offset]") sincePage: Int): Call<FilmModel>
}