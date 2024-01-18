package com.example.testlist.data.repositories

import com.example.testlist.data.network.ListApi
import com.example.testlist.utils.io

class NetworkRepositoryImpl(private val api: ListApi) : NetworkRepository {
    override suspend fun getAnnouncements() = io { api.getAnnouncements() }

}