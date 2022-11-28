package com.leti.sand_mine.DTO

data class WorkerFilterDTO(
    val fullName: String?,
    val phoneNumber: String?,
    val roles: List<String>?,
    val zoneIds: List<Long>?
) {
}