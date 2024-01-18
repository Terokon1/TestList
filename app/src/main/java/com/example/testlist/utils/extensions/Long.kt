package com.example.testlist.utils.extensions

import java.sql.Timestamp
import java.time.LocalDate
import java.util.TimeZone

fun Long.toLocalDate(): LocalDate =
    Timestamp(this).toInstant().atZone(TimeZone.getDefault().toZoneId()).toLocalDate()
