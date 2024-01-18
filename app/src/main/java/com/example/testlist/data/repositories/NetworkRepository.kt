package com.example.testlist.data.repositories

import com.example.testlist.data.Announcement
import com.example.testlist.utils.EitherE

interface NetworkRepository {
   suspend fun getAnnouncements(): EitherE<List<Announcement>>
}