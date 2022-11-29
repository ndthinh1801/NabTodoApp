package com.example.nabtodoapp.presentation.weatherlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nabtodoapp.R
import com.example.nabtodoapp.common.Constants.APP_ID_PARAM
import com.example.nabtodoapp.common.Constants.DEBOUNCE_PERIOD
import com.example.nabtodoapp.common.Constants.NUMBER_OF_FORECAST_DAY_PARAM
import com.example.nabtodoapp.common.Constants.UNITS_PARAM
import com.example.nabtodoapp.util.Async
import com.example.nabtodoapp.data.Result
import com.example.nabtodoapp.data.Result.Success
import com.example.nabtodoapp.data.model.WeatherItem
import com.example.nabtodoapp.domain.get_weather_by_city.GetWeatherByCityUseCase
import com.example.nabtodoapp.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UiState for the weather screen
 */
data class WeatherUiState(
    val weather: WeatherItem? = null,
    val isLoading: Boolean = false,
    val userMessage: Int? = null
)

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase
) : ViewModel() {

    private val _userMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _isLoading = MutableStateFlow(false)

    private val _searchFlow = MutableStateFlow<String?>(null)

    private val _weatherAsync = _searchFlow.mapLatest { cityName ->
        cityName?.let {
            getWeatherByCityUseCase.invoke(
                it,
                NUMBER_OF_FORECAST_DAY_PARAM,
                UNITS_PARAM,
                APP_ID_PARAM
            )
        }
    }.onStart { _isLoading.value = false }
        .map {
            it?.let { result -> handleResult(result) }
        }

    val uiState: StateFlow<WeatherUiState> = combine(
        _isLoading, _userMessage, _weatherAsync
    ) { isLoading, userMessage, weatherAsync ->
        when (weatherAsync) {
            is Async.Success -> {
                WeatherUiState(
                    weather = weatherAsync.data,
                    isLoading = isLoading,
                    userMessage = userMessage
                )
            }
            else -> {
                WeatherUiState(isLoading = true)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = WeatherUiState(isLoading = true)
    )

    init {
        _searchFlow.value = "Ha Noi"
    }

    fun snackbarMessageShown() {
        _userMessage.value = null
    }

    private fun showSnackbarMessage(message: Int) {
        _userMessage.value = message
    }

    private var searchJob: Job? = null

    fun searchWeather(onTextChange: String) {
        searchJob?.cancel()
        if (onTextChange.isNotBlank()) {
            searchJob = viewModelScope.launch {
                delay(DEBOUNCE_PERIOD)
                _searchFlow.value = onTextChange
            }
        }
    }

    private fun handleResult(weatherResult: Result<WeatherItem>): Async<WeatherItem?> =
        if (weatherResult is Success) {
            Async.Success(weatherResult.data)
        } else {
            //TODO catch error message here
            showSnackbarMessage(R.string.something_went_wrong)
            Async.Success(null)
        }

}