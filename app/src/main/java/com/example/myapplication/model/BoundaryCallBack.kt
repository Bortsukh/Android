package com.example.myapplication.model

import android.util.Log
import androidx.annotation.MainThread
import androidx.paging.PagedList
import com.example.myapplication.App
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.model.db.RecyclerItemDao

class BoundaryCallBack : PagedList.BoundaryCallback<RecyclerItem>() {
    private val galleryRemoteRepository: Repository = Repository()
    private val dao: RecyclerItemDao = App.instance.db.recyclerItemDao
    var lastRequestedPage = 0
    var tag: String = ""

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @MainThread
    override fun onZeroItemsLoaded() {
        requestAndSaveData(tag)
    }

    /**
     * User reached to the end of the list.
     */
    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: RecyclerItem) {
        requestAndSaveData(tag)
    }

    /**
     * every time it gets new items, boundary callback simply inserts them into the database and
     * paging library takes care of refreshing the list if necessary.
     */
    private fun insertItemsIntoDb(results: MutableList<RecyclerItem>, initial: Boolean) {
        Log.e("PostsDataSource ins: ", "Inserted on local")
        dao.saveAll(results)
    }

    override fun onItemAtFrontLoaded(itemAtFront: RecyclerItem) {
        // ignored, since we only ever append to what's in the DB
    }

    private fun requestAndSaveData(tag: String) {
        Log.e("PostsDataSource page: ", "" + lastRequestedPage)
        try {
            galleryRemoteRepository.getFromApiAndSaveToDb(lastRequestedPage)
            lastRequestedPage+=10
        } catch (exception: Exception) {
            Log.e("PostsDataSource", exception.message!!)
        }

    }

}
