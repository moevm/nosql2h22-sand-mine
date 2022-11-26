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
    val password: String? = null,
    val zonesWithAccess: List<Long?> = emptyList(),
    val shifts: List<Long?> = emptyList()
) {

    companion object {
        fun toDto(worker: Worker): WorkerDTO {
            with(worker) {
                return WorkerDTO(
                    id,
                    surname,
                    name,
                    worker.patronymic,
                    email,
                    phoneNumber,
                    passport,
                    role,
                    pass_id,
                    password,
                    zonesWithAccess.map { it.id },
                    shifts.map { it.id }
                )
            }
        }
    }

}

