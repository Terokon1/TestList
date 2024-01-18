package com.example.testlist.data.network

import com.example.testlist.data.network.models.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ListApi(private val httpClient: HttpClient) {
    suspend fun getAnnouncements(): Response =
        httpClient.get("https://api.bybit.com/v5/announcements/index?locale=en-US&limit=10").body()

}