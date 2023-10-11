package com.krupa.countrylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krupa.countrylist.api.ApiResponse
import com.krupa.countrylist.api.ApiService
import com.krupa.countrylist.api.Country
import kotlinx.coroutines.launch
import retrofit2.HttpException

//fetch data using viewModel
class CountryViewModel(private val apiService: ApiService) : ViewModel() {

    private val _countries = MutableLiveData<ApiResponse<List<Country>>>()
    val countries: LiveData<ApiResponse<List<Country>>> get() = _countries

    fun fetchCountries() {
        viewModelScope.launch {
            try {
                val response = apiService.getCountries()
                _countries.value = ApiResponse.Success(response)
            } catch (e: HttpException) {
            // Handle HTTP errors, e.g., 404 Not Found
            _countries.value = ApiResponse.Error(e)
             } catch (e: Exception) {
            // Handle other errors
            _countries.value = ApiResponse.Error(e)
            }
        }
    }
}