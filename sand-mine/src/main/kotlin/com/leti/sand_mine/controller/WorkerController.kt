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
//        val worker = workerRepository
//            .findByIdOrNull(workerId)
//            ?: throw NotFoundException()
//
//        with(worker.shifts) {
//            return map {
//                ShiftDTO(
//                    shiftId = it.id,
//                    date = it.date.asLocalDate(),
//                    attended = it.attended,
//                    zoneId = it.zone.id ?: -1
//                )
//            }
//        }
        val worker = workerRepository.findByIdOrNull(workerId)

        if (worker == null) {
            println("Error: Worker with id $workerId not found")
            throw NotFoundException()
        }

        return shiftsRepository.getAllShiftsByWorker(workerId)
            .map { shift ->
                ShiftDTO(
                    shiftId = shift.id,
                    date = shift.date.asLocalDate(),
                    attended = shift.attended,
                    zoneId = shift.id ?: -1
                )
            }.toList();
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
            it.date.asLocalDate() == LocalDate.now()
        } ?: throw NotFoundException()

        if (worker.zonesWithAccess.contains(wardenCurrentShift.zone)) {//добавляем присутствие
            val shift: Shift? = worker.shifts.find { it.date.asLocalDate() == LocalDate.now() }
            if (shift != null) {
                val newShift: Shift = shift.copy(shift.id, shift.date, true, shift.zone)
                shiftsRepository.save(newShift)
            }
        }
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


        val shifts: Set<Shift> = generateShifts(zonesWithAccess);

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
                        shifts = shifts
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

        if (workerFilterDTO.surname != null) {
            surname = "(?i)".plus(".*").plus(workerFilterDTO.surname).plus(".*");
            needFiltering = true
        }
        if (workerFilterDTO.name != null) {
            name = "(?i)".plus(".*").plus(workerFilterDTO.name).plus(".*");
            needFiltering = true
        }
        if (workerFilterDTO.patronymic != null) {
            patronymic = "(?i)".plus(".*").plus(workerFilterDTO.patronymic).plus(".*");
            needFiltering = true
        }

        if (workerFilterDTO.phoneNumber != null) {
            phoneNumber = "\\+?\\d*".plus(workerFilterDTO.phoneNumber.replace("+", "").replace(" ", "").plus("\\d*"))
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

    @GetMapping("/admins")
    fun getAdmins(): List<WorkerDTO> {
        return workerRepository.findAdmins().map { worker -> WorkerDTO.toDto(worker) }
    }

    @PostMapping("/phone")
    fun getWorkerByPhone(@RequestBody phoneNumber: String): WorkerDTO? {
        val worker: Worker = workerRepository.getWorkerByPhoneNumber(phoneNumber) ?: return null;
        return WorkerDTO(
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
    }

    @PostMapping("/email")
    fun getWorkerByEmail(@RequestBody email: String): WorkerDTO? {
        val emailFormat: String = email.substring(1, email.length - 1)
        val worker: Worker = workerRepository.getWorkerByEmail(emailFormat) ?: return null;
        return WorkerDTO(
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
    }

    @PostMapping("/remove")
    fun removeWorker(@RequestBody workerId: Long) {
        workerRepository.deleteById(workerId)
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
        for (i in -365..365) {
            val today = LocalDate.now().minusDays(i.toLong());
            if (today.dayOfWeek == DayOfWeek.SUNDAY || today.dayOfWeek == DayOfWeek.SATURDAY) {
                continue
            }
            val attended: Boolean;
            if (i > 0) {
                attended = (0..10).random() < 9;
            } else {
                attended = false;
            }
            shifts.add(
                Shift(
                    date = DateValue(today),
                    attended = attended,
                    zone = zones.random()
                )
            )
        }
        return shifts.toSet()
    }
}