package com.leti.sand_mine.DTO

import java.time.LocalDate

class AllShiftsDTO(
    val workerId: Long? = null,
    val surname: String,
    val name: String,
    val patronymic: String,
    val email: String,
    val phoneNumber: String,
    val role: String,
    val date: LocalDate,
    val zoneId: Long
)