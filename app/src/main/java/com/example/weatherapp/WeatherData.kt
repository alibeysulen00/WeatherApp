package com.example.weatherapp

data class WeatherData(
    val success: Boolean,
    val city: String,
    val result: List<Result>
)
