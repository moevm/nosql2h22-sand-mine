package com.leti.sand_mine.controller

import com.leti.sand_mine.DTO.*
import com.leti.sand_mine.domain.Worker
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

        with(worker.shifts) {
            return map {
                ShiftDTO(
                    shiftId = it.id,
                    date = it.date.asLocalDate(),
                    attended = it.attended,
                    zoneId = it.zone.id ?: -1
                )
            }
        }
    }

    @GetMapping("/all")
    fun getWorkerAll() = workerRepository
        .findAll().filterNotNull().map {
            WorkerDTO.toDto(it)
        }.toSet()

    @GetMapping("/{workerId}")
    fun getWorker(@PathVariable workerId: Long): WorkerDTO? {
        val worker = workerRepository
            .findByIdOrNull(workerId)
            ?: throw NotFoundException()
        return WorkerDTO.toDto(worker)
    }

    @GetMapping("/roles")
    fun getRolesAll(): Set<String> {
        return workerRepository.findAllRoles()
    }


    @PostMapping("/check")
    fun checkWorker(@RequestBody params: CheckWorkerRequestDTO): CheckWorkerResponseDTO? {
        val worker = workerRepository
            .findByPassId(params.passId)
            ?: throw NotFoundException()

        val warden = workerRepository
            .findByIdOrNull(params.wardenId)
            ?: throw NotFoundException()

        val wardenCurrentShift = warden.shifts.find {
            //TODO change to LocalDate.now()
            it.date.asLocalDate() == it.date.asLocalDate()
        } ?: throw NotFoundException()

        with(worker) {
            return CheckWorkerResponseDTO(
                surname = surname,
                name = name,
                patronymic = patronymic,
                email = email,
                phoneNumber = phoneNumber,
                role = role,
                passport = passport,
                zonesWithAccess = zonesWithAccess.map { it.id },
                allowed = worker.zonesWithAccess.contains(wardenCurrentShift.zone)
            )
        }
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

    @PostMapping("/ids")
    fun workersById(@RequestBody workersIds: List<Long>): List<WorkerDTO> {
        return workerRepository.findAllByIdIn(workersIds).map { worker -> WorkerDTO.toDto(worker) }
    }

    @PostMapping("/filter")
    fun getFilteredWorkers(@RequestBody workerFilterDTO: WorkerFilterDTO): Set<WorkerDTO> {

        var needFiltering = false
        var surname = "(?i).*"
        var name = "(?i).*"
        var patronymic = "(?i).*"
        var phoneNumber = "\\+?\\d*"
        var roles = ".*"
        var zoneIds = ".*"

        if (workerFilterDTO.fullName != null) {
            val splitFullName = workerFilterDTO.fullName.split(" ", "\t", "\n")
            if (splitFullName.size == 3) {
                surname = "(?i)".plus(splitFullName[0])
                name = "(?i)".plus(splitFullName[1])
                patronymic = "(?i)".plus(splitFullName[2])
            } else {
                surname = ""
                name = ""
                patronymic = ""
            }
            needFiltering = true
        }
        if (workerFilterDTO.phoneNumber != null) {
            phoneNumber = workerFilterDTO.phoneNumber.replace("+", "\\+?").replace(" ", "").plus(".*")
            needFiltering = true
        }
        if (workerFilterDTO.roles != null) {
            roles = workerFilterDTO.roles.joinToString("|")
            needFiltering = true
        }
        if (workerFilterDTO.zoneIds != null) {
            if (zoneIds.isNotEmpty()) {
                zoneIds = workerFilterDTO.zoneIds.joinToString("|")
            }
            needFiltering = true
        }

        val workersSet = if (!needFiltering) {
            workerRepository.findAll().filterNotNull().toSet()
        } else {
            workerRepository.getFilteredWorkersList(surname, name, patronymic, phoneNumber, roles, zoneIds)
        }

        return workersSet.map { worker ->
            WorkerDTO(
                worker.id,
                worker.surname,
                worker.name,
                worker.patronymic,
                worker.email,
                worker.phoneNumber,
                worker.passport,
                worker.role,
                worker.passId,
                worker.password,
                worker.zonesWithAccess.map {
                    it.id
                }
            )
        }.toSet()
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