package com.leti.sand_mine.DTO

import java.time.LocalDate

data class ShiftFilterDto (
    val dateFrom: LocalDate,
    val dateTo: LocalDate,
    val attended: Boolean,
    val zones: Set<Int>
)