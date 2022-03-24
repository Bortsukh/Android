package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.myapplication.App
import com.example.myapplication.model.BoundaryCallBack
import com.example.myapplication.model.Repository
import com.example.myapplication.model.db.RecyclerItem

class MainViewModel: ViewModel() {

    private val repository= Repository()
    private val boundaryCallback = BoundaryCallBack(repository)
    val errorFromApi  = repository.error
    val listOfObject: LiveData<PagedList<RecyclerItem>>? =
        App.instance.db.recyclerItemDao.getAll()?.toLiveData(pageSize = App.PAGE_SIZE, boundaryCallback = boundaryCallback)
    fun refreshRequestToApi() {
        boundaryCallback.requestAndSaveData()
    }
}