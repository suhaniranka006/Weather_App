package com.example.weather_app


//data classes to map json resoponse


// ✅ Root response class
// OpenWeatherMap API se pura JSON response is data class me map hoga
data class WeatherResponse(
    val name: String,          // City ka naam → e.g., "Delhi"
    val main: Main,            // Nested JSON object (temperature info)
    val weather: List<Weather> // JSON array → ek list of weather conditions
)

// ✅ Nested "main" object
// JSON ka "main": { "temp": 30.5 }
data class Main(
    val temp: Float            // Temperature in Celsius
)

// ✅ Nested "weather" array
// JSON me "weather": [ { "description": "clear sky" } ]
data class Weather(
    val description: String    // Weather description (clear sky, clouds, rain, etc.)
)
