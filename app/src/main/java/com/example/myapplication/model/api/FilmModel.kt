package com.example.myapplication.model.api

import com.google.gson.annotations.SerializedName

data class FilmModel(
    @SerializedName("data") val data: List<DataItem>
) {
    data class DataItem(
        @SerializedName("attributes") val attributes: Attributes
    ) {
        data class Attributes(
            @SerializedName("posterImage") val posterImage: PosterImage,
            @SerializedName("slug") val serverName: String,
            @SerializedName("canonicalTitle") val title: String,
            @SerializedName("description") val description: String,
        ) {
            data class PosterImage(
                @SerializedName("tiny") val tiny: String,
                @SerializedName("small") val small: String,
                @SerializedName("medium") val medium: String,
                @SerializedName("large") val large: String,
                @SerializedName("original") val original: String
            )
        }
    }
}