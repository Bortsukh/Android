package com.example.myapplication.model

import android.util.Log
import com.example.myapplication.App
import com.example.myapplication.model.api.FilmModel
import com.example.myapplication.model.db.RecyclerItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun getFromApiAndSaveToDb(offset: Int) {

        App.instance.api.getFilmListWithPages(10, offset).enqueue(object : Callback<FilmModel> {
            override fun onFailure(call: Call<FilmModel>, t: Throwable) {
                Log.d("FAILURE", "ошибка при получении данных")
            }

            override fun onResponse(
                call: Call<FilmModel>,
                response: Response<FilmModel>
            ) {
                val list = mutableListOf<RecyclerItem>()
                if (response.isSuccessful) {
                    response.body()?.data
                        ?.forEach {
                            list.add(
                                RecyclerItem(
                                    it.attributes.posterImage.medium,
                                    it.attributes.title,
                                    it.attributes.description,
                                    false
                                )
                            )
                        }
                }
                App.instance.db.recyclerItemDao.saveAll(list)
            }
        })
    }
}