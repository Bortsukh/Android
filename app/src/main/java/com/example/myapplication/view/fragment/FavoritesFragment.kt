package com.example.myapplication.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.view.activity.MainActivity
import com.example.myapplication.viewmodel.FavoritesViewModel
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.myapplication.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class FavoritesFragment : Fragment() {

    private lateinit var vm : FavoritesViewModel

    private fun deleteFromFavorites(recyclerItem: RecyclerItem, position: Int) {
        App.instance.db.recyclerItemDao.updateFavourites(recyclerItem.nameFilm, 0)
        Snackbar.make(requireView(),"Удалено из избранного", Snackbar.LENGTH_LONG).show()
    }

    private val adapter = RecyclerAdapter(object: RecyclerItemClickListener {
        override fun onItemLongClick(recyclerItem: RecyclerItem, position: Int) {
            deleteFromFavorites(recyclerItem, position)
        }

        override fun onItemShortClick(recyclerItem: RecyclerItem, position: Int) {
            val fragment = DetailsFragment()
            val arguments = Bundle().apply {
                putString(DetailsFragment.FILM_DETAILS, recyclerItem.filmDetails)
                putString(DetailsFragment.FILM_IMAGE, recyclerItem.imageFilm) }
            fragment.arguments = arguments
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.main_frame_layout, fragment).commit()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        vm.listOfObject?.observe(this, Observer<PagedList<RecyclerItem>> { list -> adapter.submitList(list) })
    }

    fun initRecycler() {
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}