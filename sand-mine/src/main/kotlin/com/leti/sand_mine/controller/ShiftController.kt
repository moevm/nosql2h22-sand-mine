package com.leti.sand_mine.controller

import com.leti.sand_mine.domain.Shift
import com.leti.sand_mine.repository.ShiftRepository
import com.leti.sand_mine.DTO.ShiftDTO
import com.leti.sand_mine.domain.Zone
import com.leti.sand_mine.exceptions.AlreadyExistsException
import com.leti.sand_mine.exceptions.NotFoundException
import com.leti.sand_mine.repository.ZoneRepository
import org.neo4j.driver.internal.value.DateValue
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors


@RestController
@RequestMapping("api/shifts")
class ShiftController(
    private val shiftRepository: ShiftRepository,
    private val zoneRepository: ZoneRepository
) {
    @PostMapping("/create")
    fun createShift(@RequestBody shiftDto: ShiftDTO): ShiftDTO? {
        if(shiftDto.shiftId != null){
            shiftRepository.findById(shiftDto.shiftId).ifPresent { throw AlreadyExistsException() }
        }
        val zone: Zone = zoneRepository.findById(shiftDto.zoneId).orElseGet { null } ?: throw NotFoundException()
        val shift: Shift = Shift(shiftDto.shiftId, DateValue(shiftDto.date), shiftDto.attended, zone)
        shiftRepository.save(shift)
        return ShiftDTO(shiftRepository.save(shift))
    }

    @GetMapping("/all")
    fun getShifts():List<ShiftDTO>{
        return shiftRepository.findAll()
            .stream()
            .map { shift->ShiftDTO(shift) }
            .collect(Collectors.toList())
    }


}