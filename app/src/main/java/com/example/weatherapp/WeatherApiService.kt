package com.example.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather/getWeather")
    fun getWeatherData(
        @Query("data.city") city: String,
        @Header("authorization") apiKey: String,
        @Header("content-type") contentType: String = "application/json"
    ): Call<WeatherData>
}