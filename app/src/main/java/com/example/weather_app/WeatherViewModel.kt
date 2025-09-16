package com.example.weather_app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


// ✅ ViewModel Layer
// - ViewModel ka kaam UI aur Repository ke beech mediator ka hai
// - UI state (LiveData) hold karta hai taaki rotation/ config change me data na loss ho
class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    // ✅ LiveData to store API response
    // UI (Activity/Fragment) isko observe karegi
    val weatherData = MutableLiveData<WeatherResponse>()

    // ✅ LiveData to store error messages
    val error = MutableLiveData<String>()

    // ✅ Function: fetchWeather()
    // UI se city name aayega, phir ye API call karega repository ke through
    fun fetchWeather(city: String) {
        // viewModelScope.launch → Coroutine background me chalegi
        viewModelScope.launch {
            try {
                // Repository se data fetch
                val response = repository.getWeather(city)

                // API ka result LiveData me post karo
                weatherData.value = response
            } catch (e: Exception) {
                // Agar API call fail ho jaye (no internet, wrong city, etc.)
                error.value = "Failed to fetch weather : ${e.message}"
            }
        }
    }
}


//viewModelScope.launch ensures non-blocking API calls.

//LiveData updates the UI automatically when data changes.