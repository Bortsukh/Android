package com.example.myapplication.model

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.App
import com.example.myapplication.model.api.Api
import com.example.myapplication.model.api.FilmModel
import com.example.myapplication.model.db.AppDataBase
import com.example.myapplication.model.db.RecyclerItem
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "Repository"
class Repository {

    @Inject
    lateinit var api: Api
    @Inject
    lateinit var db: AppDataBase

    val error: MutableLiveData<String> = MutableLiveData<String>()

    init {
        App.appComponent.injectRepository(this)
    }

//    fun getFromApiAndSaveToDb(offset: Int) {
//
//        api.getFilmListWithPages(App.PAGE_SIZE, offset).enqueue(object : Callback<FilmModel> {
//            override fun onFailure(call: Call<FilmModel>, t: Throwable) {
//                error.value = t.message
//                Log.d("FAILURE", "ошибка при получении данных")
//            }
//
//            override fun onResponse(
//                call: Call<FilmModel>,
//                response: Response<FilmModel>
//            ) {
//                val list = mutableListOf<RecyclerItem>()
//                if (response.isSuccessful) {
//                    response.body()?.data
//                        ?.forEach {
//                            list.add(
//                                RecyclerItem(
//                                    it.attributes.posterImage.medium,
//                                    it.attributes.title,
//                                    it.attributes.description,
//                                    false
//                                )
//                            )
//                        }
//                }
//                db.recyclerItemDao.saveAll(list)
//            }
//        })
//    }
//

    fun getFromApiAndSaveToDb(offset: Int): Disposable {
        Log.d(TAG, "[getFromApiAndSaveToDb] offset: $offset")
        return api.getFilmListWithPages(App.PAGE_SIZE, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                Log.v(TAG, "[getFromApiAndSaveToDb] succeed: $response")
                val items = response.data.map {
                    RecyclerItem(
                        it.attributes.posterImage.medium,
                        it.attributes.title,
                        it.attributes.description,
                        false
                    )
                }
                db.recyclerItemDao.saveAll(items) }, {
                            Log.e(TAG, "[getFromApiAndSaveToDb] failed: $it", it)
                            error.value = it.message
                })
    }

}