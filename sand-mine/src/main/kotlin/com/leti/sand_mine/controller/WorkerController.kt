package com.leti.sand_mine.controller

import com.leti.sand_mine.domain.Worker
import com.leti.sand_mine.DTO.ShiftDTO
import com.leti.sand_mine.DTO.WorkerDTO
import com.leti.sand_mine.exceptions.NotFoundException
import com.leti.sand_mine.repository.ShiftRepository
import com.leti.sand_mine.repository.WorkerRepository
import com.leti.sand_mine.repository.ZoneRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/worker")
class WorkerController(
    private val workerRepository: WorkerRepository,
    private val zoneRepository: ZoneRepository,
    private val shiftsRepository: ShiftRepository
) {

    @GetMapping("/get_shifts/{workerId}")
    fun getShifts(@PathVariable workerId: Long): List<ShiftDTO> {
        val worker = workerRepository
            .findByIdOrNull(workerId)
            ?: throw NotFoundException()
        
        with(worker.shifts) {
            return map {
                ShiftDTO(
                    shiftId = it.id,
                    date = it.date,
                    attended = it.attended,
                    zoneId = it.zone?.id
                )
            }
        }
    }

    @GetMapping("/{phone}")
    fun getWorkerByEmailOrPhone() {
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

        val shifts = workerDto
            .shifts
            .filterNotNull()
            .map { shiftsRepository.findById(it) }
            .mapNotNull { it.orElseGet { null } }
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
                        pass_id = passId,
                        password = password ?: oldWorker.password,
                        zonesWithAccess = zonesWithAccess,
                        shifts = shifts
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

        val shifts = workerDto
            .shifts
            .filterNotNull()
            .mapNotNull { shiftsRepository.findByIdOrNull(it) }
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
                        pass_id = passId,
                        password = randomPassword(),
                        zonesWithAccess = zonesWithAccess,
                        shifts = shifts
                    )
                )
            )
        }
    }

    fun randomPassword(): String {
        val length = 10
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }
}