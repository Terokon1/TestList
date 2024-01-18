package com.example.testlist.data

import java.time.LocalDate

data class Announcement(
    val dateTimestamp: LocalDate,
    val description: String,
    val tags: List<String>,
    val title: String,
    val url: String
)