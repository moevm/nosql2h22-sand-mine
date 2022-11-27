package com.leti.sand_mine.DTO

data class CheckWorkerResponseDTO(
    val surname: String,
    val name: String,
    val patronymic: String,
    val email: String,
    val phoneNumber: String,
    val role: String,
    val passport: String,
    val zonesWithAccess: List<Long?> = emptyList(),
    val allowed: Boolean
)