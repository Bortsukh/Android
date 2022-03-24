package com.example.myapplication.model

import android.util.Log
import androidx.annotation.MainThread
import androidx.paging.PagedList
import com.example.myapplication.App
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.model.db.RecyclerItemDao

class BoundaryCallBack(val repository: Repository) : PagedList.BoundaryCallback<RecyclerItem>() {
    private val dao: RecyclerItemDao = App.instance.db.recyclerItemDao
    private var lastRequestedPage = 0

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @MainThread
    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    /**
     * User reached to the end of the list.
     */
    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: RecyclerItem) {
        requestAndSaveData()
    }

    override fun onItemAtFrontLoaded(itemAtFront: RecyclerItem) {
        // ignored, since we only ever append to what's in the DB
    }

    fun requestAndSaveData() {
        Log.e("PostsDataSource page: ", "" + lastRequestedPage)
        try {
            repository.getFromApiAndSaveToDb(lastRequestedPage)
            lastRequestedPage+=App.PAGE_SIZE
        } catch (exception: Exception) {
            Log.e("PostsDataSource", exception.message!!)
        }

    }

}
