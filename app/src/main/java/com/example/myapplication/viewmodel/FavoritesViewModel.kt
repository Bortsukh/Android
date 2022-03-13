package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.view.activity.MainActivity

class FavoritesViewModel: ViewModel() {
    private var mutableListOfObject: MutableLiveData<MutableList<RecyclerItem>> = MutableLiveData<MutableList<RecyclerItem>>()
    val listOfObject: LiveData<MutableList<RecyclerItem>>
        get() = mutableListOfObject

    init {
        mutableListOfObject.value = App.instance.db.recyclerItemDao.getAllFavourites()?.toMutableList()
    }

}