package com.example.weather_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ✅ Singleton object
// Isse ensure hota hai ki poore app me ek hi Retrofit instance bane
// Bar-bar new Retrofit() call karne ki zarurat nahi
object RetrofitInstance {

    // ✅ Base URL for OpenWeatherMap API
    // Sabhi requests is base URL ke aage attach hongi
    // Example: "https://api.openweathermap.org/data/2.5/weather"
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    // ✅ Lazy initialization of API service
    // `by lazy` ka matlab: sirf pehli baar use hone par instance create hoga
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Base URL set karna (required)

            // ✅ GsonConverterFactory
            // JSON ko automatically Kotlin objects me convert karega (WeatherResponse data class)
            .addConverterFactory(GsonConverterFactory.create())

            // ✅ Retrofit object build karna
            .build()

            // ✅ ApiService interface ka implementation banana
            // Retrofit khud dynamic proxy create karega jo API ko call karega
            .create(ApiService::class.java)
    }
}
