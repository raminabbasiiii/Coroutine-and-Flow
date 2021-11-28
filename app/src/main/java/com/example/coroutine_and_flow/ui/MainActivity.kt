package com.example.coroutine_and_flow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutine_and_flow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var countryListAdapter: CountryListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureRecyclerView()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        mainViewModel.countries.observe(this) {
            countryListAdapter?.apply {
                setCountryList(it)
            }
        }
    }

    private fun configureRecyclerView() {
        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            countryListAdapter = CountryListAdapter()
            adapter = countryListAdapter
        }
    }
}