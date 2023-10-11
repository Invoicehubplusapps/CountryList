package com.krupa.countrylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krupa.countrylist.adapter.CountryAdapter
import com.krupa.countrylist.api.ApiResponse
import com.krupa.countrylist.api.ApiService

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter
    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = CountryAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Initialize ViewModel
        viewModel = CountryViewModel(ApiService.create())

        observeViewModel()

        // Fetch countries only if savedInstanceState is null,
        // so it doesn't fetch data on every configuration change
        if (savedInstanceState == null) {
            viewModel.fetchCountries()
        }
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this) { response ->
            when (response) {
                is ApiResponse.Success -> {
                    val countries = response.data
                    adapter.setData(countries)
                }
                is ApiResponse.Error -> {
                    // Handle error, e.g., show an error message
                    val error = response.exception
                    Log.e("MainActivity", "Error fetching countries", error)
                }
            }
        }
    }
}