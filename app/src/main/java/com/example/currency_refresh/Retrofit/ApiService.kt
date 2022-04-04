package com.example.currency_refresh.Retrofit

import com.example.currency_refresh.models.Currency
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("json")
    fun getCurrency(): Call<List<Currency>>
}