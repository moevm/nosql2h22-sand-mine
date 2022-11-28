package com.leti.sand_mine.DTO

import java.time.LocalDate

data class MineStatsFilterDto(
    val dateEdit: LocalDate?,
    val dateFrom: LocalDate?,
    val dateTo: LocalDate?,
    val lastEditorIds: List<Long>?,
    val weightFrom: Double?,
    val weightTo: Double?,
    val zoneIds: List<Long>?
)