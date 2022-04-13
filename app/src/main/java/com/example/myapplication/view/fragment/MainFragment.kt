package com.example.myapplication.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.*
import com.example.myapplication.model.api.FilmModel
import com.example.myapplication.model.db.RecyclerItem
import com.example.myapplication.view.activity.MainActivity
import com.example.myapplication.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    private lateinit var vm : MainViewModel

    private val adapter = RecyclerAdapter( object: RecyclerItemClickListener {
        override fun onItemLongClick(recyclerItem: RecyclerItem, position: Int) {
            recyclerItem.isFavorite = true
            App.appComponent.db().recyclerItemDao.updateFavourites(recyclerItem.nameFilm, 1)
            Toast.makeText(context,"Успешно добавлено", Toast.LENGTH_LONG).show()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        vm.listOfObject?.observe(this, Observer<PagedList<RecyclerItem>> { list -> adapter.submitList(list) })
        vm.errorFromApi?.observe(this, Observer<String> { errorMessage -> Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show() })
        val swipeRefreshLayout = requireView().findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        swipeRefreshLayout.setOnRefreshListener {
            // Initialize a new Runnable
            val runnable = Runnable {
                vm.refreshRequestToApi()
                // Hide swipe to refresh icon animation
                swipeRefreshLayout.isRefreshing = false
            }
            Thread(runnable).start()
        }
    }

    fun initRecycler() {
        val recyclerView: RecyclerView = requireView().findViewById(R.id.fragment_main)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

}