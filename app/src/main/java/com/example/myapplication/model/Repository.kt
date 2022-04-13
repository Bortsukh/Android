package com.example.myapplication.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.App
import com.example.myapplication.model.api.Api
import com.example.myapplication.model.api.FilmModel
import com.example.myapplication.model.db.AppDataBase
import com.example.myapplication.model.db.RecyclerItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository {

    @Inject
    lateinit var api: Api
    @Inject
    lateinit var db: AppDataBase

    val error: MutableLiveData<String> = MutableLiveData<String>()

    init {
        App.appComponent.injectRepository(this)
    }

    fun getFromApiAndSaveToDb(offset: Int) {

        api.getFilmListWithPages(App.PAGE_SIZE, offset).enqueue(object : Callback<FilmModel> {
            override fun onFailure(call: Call<FilmModel>, t: Throwable) {
                error.value = t.message
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
                db.recyclerItemDao.saveAll(list)
            }
        })
    }
}