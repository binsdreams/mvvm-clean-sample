package com.bins.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bins.R
import com.bins.presentation.entity.Data
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var listAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        listAdapter = UserListAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = listAdapter

        homeViewModel.fetchUsers()
    }

    override fun onStart() {
        super.onStart()

        homeViewModel.getUserLiveData().observe(this, Observer {
            when (it) {
                is Data.ERROR -> {
                    //Error handling
                }
                is Data.LOADING -> {
                    //Progress
                }
                is Data.SUCCESS -> {
                    it.data?.let { userResponse -> listAdapter.updateList(userResponse.results) }
                }
            }
        })
    }
}
