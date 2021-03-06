package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.myapplication.App
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.view.activity.MainActivity

class FavoritesViewModel: ViewModel() {
    val listOfObject: LiveData<PagedList<RecyclerItem>>? =
        App.appComponent.db().recyclerItemDao.getAllFavourites()?.toLiveData(pageSize = 10)
}