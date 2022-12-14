package com.leti.sand_mine.controller

import com.leti.sand_mine.DTO.AllShiftsDTO
import com.leti.sand_mine.domain.Shift
import com.leti.sand_mine.repository.ShiftRepository
import com.leti.sand_mine.DTO.ShiftDTO
import com.leti.sand_mine.DTO.ShiftFilterDto
import com.leti.sand_mine.DTO.WorkerWithShiftsDTO
import com.leti.sand_mine.domain.Zone
import com.leti.sand_mine.exceptions.AlreadyExistsException
import com.leti.sand_mine.exceptions.NotFoundException
import com.leti.sand_mine.repository.WorkerRepository
import com.leti.sand_mine.repository.ZoneRepository
import org.neo4j.driver.internal.value.DateValue
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import com.leti.sand_mine.DTO.AllShiftsFilterDTO
import com.leti.sand_mine.domain.Worker
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.All

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

        val sortedShifts: Set<Shift> = shiftRepository.getAllShiftsByWorker(workerId);
        worker.shifts = sortedShifts;

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
    fun getAllShifts(): List<AllShiftsDTO> {
        val result = mutableListOf<AllShiftsDTO>()
        workerRepository.findAll().map { worker ->
            with(worker) {
                result.addAll(shifts.map { shift ->
                    AllShiftsDTO(
                        id,
                        surname,
                        name,
                        patronymic,
                        email,
                        phoneNumber,
                        role,
                        shift.date.asLocalDate(),
                        shift.zone.id ?: -1,
                    )
                }.sortedBy { it.date })
            }
        }
        return result
    }

    @PostMapping("/all/filter")
    fun getAllShiftsFiltered(@RequestBody filters: AllShiftsFilterDTO): List<AllShiftsDTO> {
        val dateFrom = filters.dateFrom ?: LocalDate.MIN
        val dateTo = filters.dateTo ?: LocalDate.MAX
        val phoneRegular = filters.phone?.replace("+", "\\+?")?.replace(" ", "") ?: ".*"
        val roleRegular = filters.role ?: ".*"
        val nameRegular: String = filters.name ?: ""
        val surnameRegular: String = filters.surname ?: ""
        val patronymicRegular: String = filters.patronymic ?: ""

        val zoneIdsRegular = filters.zoneIds.takeIf { !it.isNullOrEmpty() }?.joinToString(separator = "|") ?: ".*"

        val result = mutableListOf<AllShiftsDTO>()

        val shifts: List<Shift> = shiftRepository.allShiftsFilter(
            "(?i).*${nameRegular}.*",
            "(?i).*${surnameRegular}.*",
            "(?i).*${patronymicRegular}.*",
            "(?i)[0-9]*${phoneRegular}[0-9]*",
            "(?i)${roleRegular}",
            dateFrom,
            dateTo,
            "(?i)${zoneIdsRegular}"
        )

        shifts.map { shift ->
            val worker: Worker = workerRepository.getWorkerByShift(shift.id)
            with(worker) {
                result.add(
                    AllShiftsDTO(
                        id,
                        surname,
                        name,
                        patronymic,
                        email,
                        phoneNumber,
                        role,
                        shift.date.asLocalDate(),
                        shift.zone.id ?: -1,
                    )
                )
            }
        }

//        workers.map { worker ->
//            with(worker) {
//                result.addAll(shifts.map { shift ->
//                    AllShiftsDTO(
//                        id,
//                        surname,
//                        name,
//                        patronymic,
//                        email,
//                        phoneNumber,
//                        role,
//                        shift.date.asLocalDate(),
//                        shift.zone.id ?: -1,
//                    )
//                }.sortedBy { it.date })
//            }
//        }

        return result
    }

    @PostMapping("/filter")
    fun getFilteredShifts(@RequestBody shiftFilterDto: ShiftFilterDto): Set<ShiftDTO> {
        var needFiltering = false
        var dateFrom = LocalDate.MIN
        var dateTo = LocalDate.MAX
        var attended = "(?i).*"
        var zoneIds = "(?i).*"

        if (shiftFilterDto.dateFrom != null) {
            dateFrom = shiftFilterDto.dateFrom
            needFiltering = true
        }
        if (shiftFilterDto.dateTo != null) {
            dateTo = shiftFilterDto.dateTo
            needFiltering = true
        }
        if (shiftFilterDto.attended != null) {
            attended = "(?i)".plus(shiftFilterDto.attended.toString())
            needFiltering = true
        }
        if (shiftFilterDto.zoneIds != null) {
            zoneIds = "(?i)".plus(shiftFilterDto.zoneIds.joinToString("|"))
            needFiltering = true
        }

        val shiftSet = if (!needFiltering) {
            shiftRepository.getAllShiftsByWorker(shiftFilterDto.workerId).filterNotNull().toSet()
        } else {
            shiftRepository.getFilteredShiftList(shiftFilterDto.workerId, dateFrom, dateTo, attended, zoneIds)
        }

        return shiftSet.map { shift ->
            with(shift) {
                ShiftDTO(
                    id,
                    date.asLocalDate(),
                    this.attended,
                    zone.id ?: -1
                )
            }
        }.toSet()
    }
}