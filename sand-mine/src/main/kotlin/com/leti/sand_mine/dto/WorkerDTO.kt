package com.leti.sand_mine.DTO

import com.leti.sand_mine.domain.Worker

data class WorkerDTO(
    val workerId: Long? = null,
    val surname: String,
    val name: String,
    val patronymic: String,
    val email: String,
    val phoneNumber: String,
    val passport: String,
    val role: String,
    val passId: Long,
    val zonesWithAccess: List<Long?> = emptyList(),
) {

    companion object {
        fun toDto(worker: Worker): WorkerDTO {
            with(worker) {
                return WorkerDTO(
                    id,
                    surname,
                    name,
                    patronymic,
                    email,
                    phoneNumber,
                    passport,
                    role,
                    passId,
                    zonesWithAccess.map { it.id }
                )
            }
        }
    }
}

