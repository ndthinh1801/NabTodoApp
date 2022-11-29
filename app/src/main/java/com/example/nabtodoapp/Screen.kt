package com.example.nabtodoapp

sealed class Screen(val route: String) {
    object WeatherScreen : Screen("weather_screen")
}
