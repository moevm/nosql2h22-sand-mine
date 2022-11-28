package com.leti.sand_mine.DTO

import java.time.LocalDate

data class ShiftFilterDto(
    val workerId: Long,
    val dateFrom: LocalDate?,
    val dateTo: LocalDate?,
    val attended: Boolean?,
    val zoneIds: List<Long>?
)