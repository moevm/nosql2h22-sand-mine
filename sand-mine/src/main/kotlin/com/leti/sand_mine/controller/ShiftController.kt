package com.leti.sand_mine.controller

import com.leti.sand_mine.DTO.AuthResponseDto
import com.leti.sand_mine.domain.Shift
import com.leti.sand_mine.repository.ShiftRepository
import com.leti.sand_mine.DTO.ShiftDTO
import com.leti.sand_mine.DTO.WorkerWithShiftsDTO
import com.leti.sand_mine.domain.Worker
import com.leti.sand_mine.domain.Zone
import com.leti.sand_mine.exceptions.AlreadyExistsException
import com.leti.sand_mine.exceptions.NotFoundException
import com.leti.sand_mine.repository.WorkerRepository
import com.leti.sand_mine.repository.ZoneRepository
import org.neo4j.driver.internal.value.DateValue
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/shifts")
class ShiftController(
    private val shiftRepository: ShiftRepository,
    private val zoneRepository: ZoneRepository,
    private val workerRepository: WorkerRepository
) {
    @PostMapping("/create")
    fun createShift(@RequestBody shiftDto: ShiftDTO): ShiftDTO? {
        if (shiftDto.shiftId != null) {
            shiftRepository.findById(shiftDto.shiftId).ifPresent { throw AlreadyExistsException() }
        }
        val zone: Zone = zoneRepository.findById(shiftDto.zoneId).orElseGet { null } ?: throw NotFoundException()
        val shift: Shift = Shift(shiftDto.shiftId, DateValue(shiftDto.date), shiftDto.attended, zone)
        shiftRepository.save(shift)
        return ShiftDTO(shiftRepository.save(shift))
    }

    @GetMapping("/{workerId}")
    fun getWorkerShifts(@PathVariable workerId: Long): WorkerWithShiftsDTO {
        val worker = workerRepository.findByIdOrNull(workerId)

        if (worker == null) {
            println("Error: Worker with id $workerId not found")
            throw NotFoundException()
        }

        return with(worker) {
            WorkerWithShiftsDTO(
                id,
                surname,
                name,
                patronymic,
                email,
                phoneNumber,
                role,
                shifts.map { shift ->
                    WorkerWithShiftsDTO.Shift(
                        shift.date.asLocalDate(),
                        shift.zone.id ?: -1,
                        shift.attended
                    )
                }
            )
        }
    }

    @GetMapping("/all")
    fun getAllShifts() = workerRepository.findAll().map { worker ->
            with(worker) {
                WorkerWithShiftsDTO(
                    id,
                    surname,
                    name,
                    patronymic,
                    email,
                    phoneNumber,
                    role,
                    shifts.map { shift ->
                        WorkerWithShiftsDTO.Shift(
                            shift.date.asLocalDate(),
                            shift.zone.id ?: -1,
                            shift.attended
                        )
                    }
                )
            }
        }
}