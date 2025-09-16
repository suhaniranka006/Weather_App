package com.example.weather_app

// ✅ Repository Layer
// Ye class ka kaam sirf API call karke data ko fetch karna hai
// ViewModel aur Repository ke beech communication hota hai
class WeatherRepository {

    // ✅ Function: getWeather()
    // suspend → coroutine ke andar call hoga (network request async hote hain)
    // city → user se liya hua city name
    // return type → WeatherResponse (API se aaya pura JSON mapped object)
    suspend fun getWeather(city: String): WeatherResponse {
        return RetrofitInstance.api.getWeather(
            city, // user input city name
            apiKey = "ddd68b88e31bb6a89b07767a5d2808d8" // OpenWeatherMap API key
        )
    }
}



//Repository abstracts network calls from ViewModel.
//
//Keeps code modular and testable.