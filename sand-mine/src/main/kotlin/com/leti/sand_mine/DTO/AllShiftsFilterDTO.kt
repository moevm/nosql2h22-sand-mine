package com.leti.sand_mine.DTO

import java.time.LocalDate

class AllShiftsFilterDTO(
    val fullName: String? = null,
    val phone: String? = null,
    val role: String? = null,
    val zoneIds: List<Long>? = null,
    val dateFrom: LocalDate? = null,
    val dateTo: LocalDate? = null
)