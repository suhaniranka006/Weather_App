package com.example.weather_app

import retrofit2.http.GET
import retrofit2.http.Query

// ✅ Retrofit interface: Ye contract define karta hai ki kaunse API endpoints use honge
// Retrofit runtime pe is interface ka implementation khud generate karta hai
interface ApiService {

    // ✅ Weather endpoint (GET request)
    // Base URL tum Retrofit builder me doge:
    //   "https://api.openweathermap.org/data/2.5/"
    // Iske baad yahan jo path diya hai "weather" wo attach ho jayega:
    //   final URL = baseUrl + "weather" + query parameters
    @GET("weather")
    suspend fun getWeather(

        // ✅ Query parameter: "q"
        // API me user jo city type karega wo yahan jayega
        // Example: ?q=Delhi
        @Query("q") cityName: String,

        // ✅ Query parameter: "appid"
        // Ye tumhari API key hai (OpenWeatherMap se milegi)
        // Example: &appid=123456abcd
        @Query("appid") apiKey: String,

        // ✅ Query parameter: "units"
        // Default = "metric" (Celsius)
        // Agar user nahi dega, toh automatically metric use hoga
        // Example: &units=metric
        @Query("units") units: String = "metric"
    ): WeatherResponse   // ✅ Return type
    // API response ko WeatherResponse data class me map karega
}
