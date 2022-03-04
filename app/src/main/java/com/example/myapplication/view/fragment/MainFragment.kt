package com.example.myapplication.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.example.myapplication.model.api.FilmModel
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.view.activity.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    var listOfObject: MutableList<RecyclerItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return initRecycler(view)
    }

    fun initRecycler(view: View) : View{
        val recyclerView: RecyclerView = view as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(listOfObject, object: RecyclerItemClickListener {
            override fun onItemLongClick(recyclerItem: RecyclerItem, position: Int) {
                recyclerItem.isFavorite = true
                MainActivity.favoriteList.add(recyclerItem)
                Toast.makeText(context,"Успешно добавлено", Toast.LENGTH_LONG).show()
                Log.d("1234567", MainActivity.favoriteList.size.toString())
            }

            override fun onItemShortClick(recyclerItem: RecyclerItem, position: Int) {
                val fragment = DetailsFragment()
                val arguments = Bundle().apply {
                    putString(FILM_DETAILS, recyclerItem.filmDetails)
                    putString(FILM_IMAGE, recyclerItem.imageFilm) }
                fragment.arguments = arguments
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, fragment).commit()
            }
        })
        MainActivity.mainRecyclerAdapter = recyclerView.adapter as RecyclerAdapter
        getList()
        return recyclerView
    }

    fun getList() {

        App.instance.api.getFilmListWithPages().enqueue(object : Callback<FilmModel> {
            override fun onFailure(call: Call<FilmModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<FilmModel>,
                response: Response<FilmModel>
            ) {
                listOfObject.clear()
                if (response.isSuccessful) {
                    response.body()?.data
                        ?.forEach {
                            listOfObject.add(
                                RecyclerItem(
                                    it.attributes.posterImage.medium,
                                    it.attributes.title,
                                    it.attributes.description,
                                    false
                                )
                            )
                        }
                }
                MainActivity.mainRecyclerAdapter?.notifyDataSetChanged()
            }
        })
    }

    companion object {
        const val FILM_DETAILS = "FILM_NAME"
        const val FILM_IMAGE = "FILM_IMAGE"
    }

}