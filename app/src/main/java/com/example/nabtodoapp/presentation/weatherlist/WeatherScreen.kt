package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nabtodoapp.NABTodoApplication
import com.example.nabtodoapp.R
import com.example.nabtodoapp.presentation.weatherlist.WeatherViewModel
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.components.WeatherItemView
import com.plcoding.cryptocurrencyappyt.presentation.ui.theme.NABTodoAppTheme

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.label_weather_forecast)) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) { paddingValues ->
        val state by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                BasicTextField(
                    value = "",
                    onValueChange = { value -> viewModel.searchWeather(value) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
                    singleLine = true
                )
                state.weather?.let { weather ->
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(weather.list) { dayInfo ->
                            WeatherItemView(
                                dayInfo = dayInfo
                            )
                        }
                    }
                }
                state.userMessage?.let { message ->
                    val snackbarText = stringResource(message)
                    LaunchedEffect(scaffoldState, viewModel, message, snackbarText) {
                        scaffoldState.snackbarHostState.showSnackbar(snackbarText)
                        viewModel.snackbarMessageShown()
                    }
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NABTodoAppTheme {
        val navController = rememberNavController()
        WeatherScreen(navController)
    }
}