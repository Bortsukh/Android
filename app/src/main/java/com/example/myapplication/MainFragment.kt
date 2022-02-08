package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {

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
        recyclerView.adapter = RecyclerAdapter(getList() as MutableList<RecyclerItem>, object: RecyclerItemClickListener{
            override fun onItemLongClick(recyclerItem: RecyclerItem, position: Int) {
                recyclerItem.isFavorite = true
                MainActivity.favoriteList.add(recyclerItem)
                Log.d("1234567", MainActivity.favoriteList.size.toString())
            }

            override fun onItemShortClick(recyclerItem: RecyclerItem, position: Int) {
                val fragment = DetailsFragment()
                val arguments = Bundle().apply {
                    putString(FILM_DETAILS, recyclerItem.filmDetails)
                    putInt(FILM_IMAGE, recyclerItem.imageFilm) }
                fragment.arguments = arguments
                activity!!.supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, fragment).commit()
            }
        })
        MainActivity.mainRecyclerAdapter = recyclerView.adapter as RecyclerAdapter
        return recyclerView
    }

    fun getList() : List<RecyclerItem> {
        val list = mutableListOf<RecyclerItem>()
        list.add(RecyclerItem(R.drawable.harry_potter, getString(R.string.harry_potter), getString(R.string.harry_potter_description),false))
        list.add(RecyclerItem(R.drawable.star_wars, getString(R.string.star_wars), getString(R.string.star_wars_description), false))
        list.add(RecyclerItem(R.drawable.back_to_the_future, getString(R.string.back_to_future), getString(R.string.back_to_future_description),false))
        list.add(RecyclerItem(R.drawable.harry_potter, getString(R.string.harry_potter), getString(R.string.harry_potter_description),false))
        list.add(RecyclerItem(R.drawable.star_wars, getString(R.string.star_wars), getString(R.string.star_wars_description),false))
        list.add(RecyclerItem(R.drawable.back_to_the_future, getString(R.string.back_to_future), getString(R.string.back_to_future_description),false))
        list.add(RecyclerItem(R.drawable.harry_potter, getString(R.string.harry_potter), getString(R.string.harry_potter_description),false))
        list.add(RecyclerItem(R.drawable.star_wars, getString(R.string.star_wars), getString(R.string.star_wars_description),false))
        list.add(RecyclerItem(R.drawable.back_to_the_future, getString(R.string.back_to_future), getString(R.string.back_to_future_description),false))
        return list
    }


    companion object {
        const val FILM_DETAILS = "FILM_NAME"
        const val FILM_IMAGE = "FILM_IMAGE"
    }

}