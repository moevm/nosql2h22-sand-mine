package com.leti.sand_mine.DTO

data class WorkerFilterDTO(
    val surname: String?,
    val name: String?,
    val patronymic: String?,
    val phoneNumber: String?,
    val roles: List<String>?,
    val zoneIds: List<Long>?
) {
}