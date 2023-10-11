package com.krupa.countrylist.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("countries.json")
    suspend fun getCountries(): List<Country>


    companion object {
        // Assuming you have Retrofit configured in a similar way
        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}