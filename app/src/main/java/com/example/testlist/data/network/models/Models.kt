package com.example.testlist.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementResponse(
    val result: Result,
)

@Serializable
data class Result(
    val list: List<Announcement>,
    val total: Int
)

@Serializable
data class Announcement(
    val dateTimestamp: Long,
    val description: String,
    val endDateTimestamp: Long,
    val startDateTimestamp: Long,
    val tags: List<String>,
    val title: String,
    val type: Type,
    val url: String
)

@Serializable
data class Type(
    val key: String,
    val title: String
)