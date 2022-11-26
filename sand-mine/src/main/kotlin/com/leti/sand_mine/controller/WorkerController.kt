package com.leti.sand_mine.controller

import com.leti.sand_mine.domain.Worker
import com.leti.sand_mine.DTO.ShiftDTO
import com.leti.sand_mine.DTO.WorkerDTO
import com.leti.sand_mine.domain.Shift
import com.leti.sand_mine.domain.Zone
import com.leti.sand_mine.exceptions.NotFoundException
import com.leti.sand_mine.repository.ShiftRepository
import com.leti.sand_mine.repository.WorkerRepository
import com.leti.sand_mine.repository.ZoneRepository
import org.neo4j.driver.internal.value.DateValue
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.DayOfWeek
import java.time.LocalDate

@RestController
@RequestMapping("api/worker")
class WorkerController(
    private val workerRepository: WorkerRepository,
    private val zoneRepository: ZoneRepository,
    private val shiftsRepository: ShiftRepository
) {

    @GetMapping("/get_shifts/{workerId}")
    fun getWorkerShifts(@PathVariable workerId: Long): List<ShiftDTO> {
        val worker = workerRepository
            .findByIdOrNull(workerId)
            ?: throw NotFoundException()
        
        return worker.shifts.map {
            ShiftDTO(it)
        }
    }

    @GetMapping("/{workerId}")
    fun getWorker(@PathVariable workerId: Long): WorkerDTO? {
        val worker = workerRepository
            .findByIdOrNull(workerId)
            ?: throw NotFoundException()
        return WorkerDTO.toDto(worker)
    }

    @PostMapping("/edit")
    fun editWorker(@RequestBody workerDto: WorkerDTO): WorkerDTO? {
        val oldWorker = workerRepository
            .findByIdOrNull(workerDto.workerId ?: -1)
            ?: throw NotFoundException()

        val zonesWithAccess = workerDto
            .zonesWithAccess
            .filterNotNull()
            .mapNotNull { zoneRepository.findByIdOrNull(it) }
            .toSet()

        with(workerDto) {
            return WorkerDTO.toDto(
                workerRepository.save(
                    Worker(
                        id = oldWorker.id,
                        surname = surname,
                        name = name,
                        patronymic = patronymic,
                        email = email,
                        phoneNumber = phoneNumber,
                        passport = passport,
                        role = role,
                        passId = passId,
                        password = oldWorker.password,
                        zonesWithAccess = zonesWithAccess,
                        shifts = oldWorker.shifts
                    )
                )
            )
        }
    }

    @PostMapping("/new")
    fun addWorker(@RequestBody workerDto: WorkerDTO): WorkerDTO? {
        val zonesWithAccess = workerDto
            .zonesWithAccess
            .filterNotNull()
            .mapNotNull { zoneRepository.findByIdOrNull(it) }
            .toSet()

        with(workerDto) {
            return WorkerDTO.toDto(
                workerRepository.save(
                    Worker(
                        surname = surname,
                        name = name,
                        patronymic = patronymic,
                        email = email,
                        phoneNumber = phoneNumber,
                        passport = passport,
                        role = role,
                        passId = passId,
                        password = randomPassword(),
                        zonesWithAccess = zonesWithAccess,
                        shifts = generateShifts(zonesWithAccess)
                    )
                )
            )
        }
    }

    private fun randomPassword(): String {
        val length = 10
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun generateShifts(zones: Set<Zone>): Set<Shift> {
        val shifts = mutableListOf<Shift>()
        for (i in 1..365) {
            val today = LocalDate.now()
            if (today.dayOfWeek == DayOfWeek.SUNDAY || today.dayOfWeek == DayOfWeek.SATURDAY) {
                continue
            }
            shifts.add(
                Shift(
                    date = DateValue(today),
                    attended = (0..10).random() < 9,
                    zone = zones.random()
                )
            )
        }
        return shifts.toSet()
    }
}