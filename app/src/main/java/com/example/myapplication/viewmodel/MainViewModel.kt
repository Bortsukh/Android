package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.model.api.FilmModel
import com.example.myapplication.model.db.RecyclerItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private var mutableListOfObject: MutableLiveData<MutableList<RecyclerItem>> = MutableLiveData<MutableList<RecyclerItem>>()
    val listOfObject: LiveData<MutableList<RecyclerItem>>
        get() = mutableListOfObject

    init {
        getListMainViewModel()
    }

    private fun getListMainViewModel() {

        App.instance.api.getFilmListWithPages().enqueue(object : Callback<FilmModel> {
            override fun onFailure(call: Call<FilmModel>, t: Throwable) {
                mutableListOfObject.value = App.instance.db.recyclerItemDao.getAll()?.toMutableList()
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
                mutableListOfObject.value = list
                App.instance.db.recyclerItemDao.saveAll(list)
            }
        })
    }
}