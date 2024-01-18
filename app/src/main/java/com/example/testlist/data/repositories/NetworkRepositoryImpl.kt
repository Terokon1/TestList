package com.example.testlist.data.repositories

import com.example.testlist.data.Announcement
import com.example.testlist.data.network.ListApi
import com.example.testlist.utils.extensions.toLocalDate
import com.example.testlist.utils.io

class NetworkRepositoryImpl(private val api: ListApi) : NetworkRepository {

    override suspend fun getAnnouncements() = io {
        api.getAnnouncements().result.list.map {
            Announcement(
                dateTimestamp = it.dateTimestamp.toLocalDate(),
                description = it.description,
                tags = it.tags,
                title = it.title,
                url = it.url
            )
        }
    }
}