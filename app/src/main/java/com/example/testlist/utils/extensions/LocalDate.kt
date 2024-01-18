package com.example.testlist.utils.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.formatStandard(formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")): String =
    format(formatter)