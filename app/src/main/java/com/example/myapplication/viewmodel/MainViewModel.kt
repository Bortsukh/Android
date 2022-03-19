package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.myapplication.App
import com.example.myapplication.model.BoundaryCallBack
import com.example.myapplication.model.db.RecyclerItem

class MainViewModel: ViewModel() {

    val listOfObject: LiveData<PagedList<RecyclerItem>>? =
        App.instance.db.recyclerItemDao.getAll()?.toLiveData(pageSize = 10, boundaryCallback = BoundaryCallBack())
}