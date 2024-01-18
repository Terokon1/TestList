package com.example.testlist.utils.extensions

import java.sql.Timestamp
import java.util.TimeZone

fun Long.toLocalDate() =
    Timestamp(this).toInstant().atZone(TimeZone.getDefault().toZoneId()).toLocalDate()
