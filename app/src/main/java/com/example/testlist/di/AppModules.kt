package com.example.testlist.di

import com.example.testlist.data.network.ListApi
import com.example.testlist.data.repositories.NetworkRepository
import com.example.testlist.data.repositories.NetworkRepositoryImpl
import com.example.testlist.ui.list.ListViewModel
import com.example.testlist.utils.CustomHttpLogger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single {
        HttpClient {
            install(Logging) {
                logger = CustomHttpLogger()
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }


        }
    }
    single { ListApi(httpClient = get()) }
    single<NetworkRepository> { NetworkRepositoryImpl(api = get()) }
    viewModel { ListViewModel(networkRepository = get()) }
}