package com.leti.sand_mine.controller

import com.leti.sand_mine.DTO.AuthDto
import com.leti.sand_mine.DTO.AuthResponseDto
import com.leti.sand_mine.domain.Worker
import com.leti.sand_mine.exceptions.NotFoundException
import com.leti.sand_mine.repository.WorkerRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class MainController(
    private val workerRepository: WorkerRepository
) {
    @GetMapping
    fun main():String{
        return "index"
    }


    @GetMapping("/*")//Для того чтобы не было ошибки 404 в консоли
    fun allRequests():String{
        return "forward:/"
    }

    @PostMapping("/auth")
    fun login(@RequestBody authDto: AuthDto): AuthResponseDto {
        val worker : Worker? = workerRepository.findByLogin(authDto.login)
        if (worker == null) {
            println("Error: Worker with id ${authDto.login} not found")
            throw NotFoundException()
        }

        val workerId = worker.id
        if (workerId == null) {
            println("Error: Worker with id $workerId not found")
            throw NotFoundException()
        }

        return AuthResponseDto(
            workerId,
            worker.role
        )
    }
}