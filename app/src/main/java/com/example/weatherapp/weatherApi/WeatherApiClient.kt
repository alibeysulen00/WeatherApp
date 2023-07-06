package com.example.weatherapp.weatherApi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiClient {

    private const val BASE_URL = "https://api.collectapi.com/"
    private const val API_KEY = "apikey 6MWK3Amkt3GjrKo8xqdjuZ:0ZuvWmd2ejiz8bcGA67COW"


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: WeatherApiService = retrofit.create(WeatherApiService::class.java)

    fun getWeatherData(city: String): Call<WeatherData> {
        return service.getWeatherData(city,"tr", API_KEY, "application/json")
    }
}
