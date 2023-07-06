package com.example.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var horizontalScrollView: HorizontalScrollView
    private lateinit var date: TextView
    private lateinit var description: TextView
    private lateinit var day: TextView
    private lateinit var istanbul_btn: Button
    private lateinit var ankara_btn: Button
    private lateinit var elazig_btn: Button
    private lateinit var pazartesi_btn: Button
    private lateinit var sali_btn: Button
    private lateinit var carsamba_btn: Button
    private lateinit var persembe_btn: Button
    private lateinit var cuma_btn: Button
    private lateinit var cumartesi_btn: Button
    private lateinit var pazar_btn: Button
    private lateinit var degree: Button
    private lateinit var cityImage: ImageView
    private lateinit var iconImage: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        date = findViewById(R.id.date)
        description = findViewById(R.id.description)
        day = findViewById(R.id.day)
        istanbul_btn = findViewById(R.id.istanbul)
        ankara_btn = findViewById(R.id.ankara)
        elazig_btn = findViewById(R.id.elazig)
        pazartesi_btn = findViewById(R.id.btn_pazartesi)
        sali_btn = findViewById(R.id.btn_sali)
        carsamba_btn = findViewById(R.id.btn_carsamba)
        persembe_btn = findViewById(R.id.btn_persembe)
        cuma_btn = findViewById(R.id.btn_cuma)
        cumartesi_btn = findViewById(R.id.btn_cumartesi)
        pazar_btn = findViewById(R.id.btn_pazar)
        degree = findViewById(R.id.degree)
        cityImage = findViewById(R.id.city_image)
        iconImage = findViewById(R.id.iconimage)
        horizontalScrollView = findViewById(R.id.horizontalScrollView)




        val call: Call<WeatherData> = WeatherApiClient.getWeatherData("ankara")
        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (response.isSuccessful) {
                    val weatherData: WeatherData? = response.body()
                    // Hava durumu verileriyle istediğiniz işlemleri yapabilirsiniz
                    if (weatherData != null && weatherData.result.isNotEmpty()) {
                        date.text = weatherData.result[0].date
                        description.text = weatherData.result[0].description
                        day.text = weatherData.result[0].day
                    }
                } else {
                    // İstek başarısız oldu, hata işleme yapabilirsiniz
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                // İstek başarısız oldu, hata işleme yapabilirsiniz
            }
        })
    }
}
