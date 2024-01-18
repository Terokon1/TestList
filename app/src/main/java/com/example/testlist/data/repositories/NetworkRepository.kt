package com.example.testlist.data.repositories

import com.example.testlist.data.network.ListApi
import com.example.testlist.data.network.models.AnnouncementResponse
import com.example.testlist.utils.EitherE

interface NetworkRepository {
   suspend fun getAnnouncements(): EitherE<AnnouncementResponse>
}