package com.example.testlist.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementResponse(
    val result: ResultResponse,
) {
    @Serializable
    data class ResultResponse(
        val list: List<ItemResponse>,
        val total: Int
    )

    @Serializable
    data class ItemResponse(
        val dateTimestamp: Long,
        val description: String,
        val endDateTimestamp: Long,
        val startDateTimestamp: Long,
        val tags: List<String>,
        val title: String,
        val type: TypeResponse,
        val url: String
    )

    @Serializable
    data class TypeResponse(
        val key: String,
        val title: String
    )
}