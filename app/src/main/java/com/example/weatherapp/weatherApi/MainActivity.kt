package com.example.weatherapp.weatherApi

import android.os.Bundle
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var horizontalScrollView: HorizontalScrollView
    private lateinit var date: TextView
    private lateinit var description: TextView
    private lateinit var dayText: TextView
    private lateinit var pazartesi_btn: Button
    private lateinit var sali_btn: Button
    private lateinit var carsamba_btn: Button
    private lateinit var persembe_btn: Button
    private lateinit var cuma_btn: Button
    private lateinit var cumartesi_btn: Button
    private lateinit var pazar_btn: Button
    private lateinit var degree: Button
    private lateinit var iconImage: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        date = findViewById(R.id.date)
        description = findViewById(R.id.description)
        dayText = findViewById(R.id.day)
        pazartesi_btn = findViewById(R.id.btn_pazartesi)
        sali_btn = findViewById(R.id.btn_sali)
        carsamba_btn = findViewById(R.id.btn_carsamba)
        persembe_btn = findViewById(R.id.btn_persembe)
        cuma_btn = findViewById(R.id.btn_cuma)
        cumartesi_btn = findViewById(R.id.btn_cumartesi)
        pazar_btn = findViewById(R.id.btn_pazar)
        degree = findViewById(R.id.degree)
        iconImage = findViewById(R.id.iconimage)
        horizontalScrollView = findViewById(R.id.horizontalScrollView)

        pazartesi_btn.setOnClickListener { handleButtonClick("Pazartesi") }
        sali_btn.setOnClickListener { handleButtonClick("Salı") }
        carsamba_btn.setOnClickListener { handleButtonClick("Çarşamba") }
        persembe_btn.setOnClickListener { handleButtonClick("Perşembe") }
        cuma_btn.setOnClickListener { handleButtonClick("Cuma") }
        cumartesi_btn.setOnClickListener { handleButtonClick("Cumartesi") }
        pazar_btn.setOnClickListener { handleButtonClick("Pazar") }


    }
    private fun handleButtonClick(day: String) {
        // Buton tıklanmasını işleme alma
        // Seçilen günü kullanarak hava durumu verilerini al ve ekrana yansıt

        // CollectAPI'den hava durumu verilerini almak için WeatherApiClient kullanılıyor
        val call: Call<WeatherData> = WeatherApiClient.getWeatherData("istanbul")
        call.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (response.isSuccessful) {
                    val weatherData: WeatherData? = response.body()
                    if (weatherData != null && weatherData.result.isNotEmpty()) {
                        // Seçilen günün hava durumu bilgilerini al
                        val selectedDayWeather = weatherData.result.find { it.day.equals(day, ignoreCase = true) }

                        if (selectedDayWeather != null) {
                            var iconUrl = selectedDayWeather.icon
                            Picasso.get().load(iconUrl).into(iconImage)
                            date.text = selectedDayWeather.date
                            description.text = selectedDayWeather.description
                            degree.text = selectedDayWeather.degree
                            dayText.text = selectedDayWeather.day
                        } else {
                            // Seçilen günün hava durumu verisi bulunamadı
                            resetWeatherInfo()
                        }
                    } else {
                        // Hava durumu verisi bulunamadı
                        resetWeatherInfo()
                    }
                } else {
                    // İstek başarısız oldu, hata işleme yapabilirsiniz
                    resetWeatherInfo()
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                // İstek başarısız oldu, hata işleme yapabilirsiniz
                resetWeatherInfo()
            }
        })
    }

    private fun resetWeatherInfo() {
        // Hava durumu bilgilerini sıfırla
        date.text = ""
        description.text = ""
        degree.text = ""
    }
}