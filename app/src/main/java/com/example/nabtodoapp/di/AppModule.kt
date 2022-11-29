package com.example.nabtodoapp.di

import android.content.Context
import androidx.room.Room
import com.example.nabtodoapp.common.Constants
import com.example.nabtodoapp.data.local.AppDataBase
import com.example.nabtodoapp.data.local.LocalService
import com.example.nabtodoapp.data.remote.ApiService
import com.example.nabtodoapp.data.remote.NetworkService
import com.example.nabtodoapp.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            "NABTodo.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkService(apiService: ApiService): NetworkService {
        return NetworkService(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalService(dataBase: AppDataBase,
                            @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): LocalService {
        return LocalService(dataBase.weatherItemDao(), ioDispatcher)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        networkService: NetworkService,
        localService: LocalService
    ): WeatherRepository {
        return WeatherRepository(networkService, localService)
    }
}