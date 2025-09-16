package com.example.weather_app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ✅ ViewBinding → layout ke views direct access karne ke liye
    private lateinit var binding: ActivityMainBinding

    // ✅ ViewModel initialization
    // - by viewModels delegate use ho raha hai
    // - custom Factory provide kiya hai kyunki WeatherViewModel constructor me repository chahiye
    private val viewModel : WeatherViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                // Yaha WeatherRepository inject ho raha hai ViewModel me
                return WeatherViewModel(WeatherRepository()) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Status bar/ navigation ke layout me properly adjust karne ke liye

        // ✅ Binding initialize
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Button click listener → city input lene ke baad weather fetch karega
        binding.btnFetch.setOnClickListener {
            val city = binding.etCity.text.toString()

            if(city.isNotEmpty()) {
                // Agar user ne city enter ki hai to API call kare
                viewModel.fetchWeather(city)
            } else {
                // Agar input empty hai to Toast show karo
                Toast.makeText(this, "Enter city name", Toast.LENGTH_SHORT).show()
            }
        }

        // ✅ LiveData Observe: Weather data ka result aane par UI update hoga
        viewModel.weatherData.observe(this) { weather ->
            binding.tvCity.text = weather.name                     // City ka naam
            binding.tvTemp.text = "${weather.main.temp}°C"         // Temperature
            binding.tvCondition.text = weather.weather[0].description // Weather description
        }

        // ✅ LiveData Observe: Error handle karo
        viewModel.error.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }


    }
}
