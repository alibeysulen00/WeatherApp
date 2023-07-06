package com.example.weatherapp.weatherApi

import com.example.weatherapp.weatherApi.Result

data class WeatherData(
    val success: Boolean,
    val city: String,
    val result: List<Result>
)
