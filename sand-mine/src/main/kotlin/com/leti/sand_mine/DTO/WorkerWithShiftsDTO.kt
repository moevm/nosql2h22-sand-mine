package com.leti.sand_mine.DTO

import java.time.LocalDate

data class WorkerWithShiftsDTO(
    val workerId: Long? = null,
    val surname: String,
    val name: String,
    val patronymic: String,
    val email: String,
    val phoneNumber: String,
    val role: String,
    val shifts: List<Shift> = emptyList()
) {
    data class Shift(
        val date: LocalDate,
        val zoneId: Long,
        val attended: Boolean
    )
}