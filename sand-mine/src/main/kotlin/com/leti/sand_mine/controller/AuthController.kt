package com.leti.sand_mine.controller

import com.leti.sand_mine.DTO.AuthDto
import com.leti.sand_mine.DTO.AuthResponseDto
import com.leti.sand_mine.domain.Worker
import com.leti.sand_mine.exceptions.NotFoundException
import com.leti.sand_mine.repository.WorkerRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class AuthController(
    private val workerRepository: WorkerRepository
) {
    @PostMapping("/auth")
    fun login(@RequestBody authDto: AuthDto): AuthResponseDto {
        val worker: Worker? = workerRepository.findByLogin(authDto.login.lowercase(Locale.getDefault()))
        if (worker == null) {
            println("Error: Worker with id ${authDto.login} not found")
            throw NotFoundException()
        }

        val workerId = worker.id
        if (workerId == null) {
            println("Error: Worker with id $workerId not found")
            throw NotFoundException()
        }

        if (worker.password != authDto.password) {
            throw NotFoundException()
        }

        return AuthResponseDto(
            workerId,
            worker.role
        )
    }
}