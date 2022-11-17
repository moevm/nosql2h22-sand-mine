package com.leti.sand_mine.controller

import com.leti.sand_mine.domain.Shift
import com.leti.sand_mine.repository.ShiftRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.leti.sand_mine.DTO.ShiftDTO
import com.leti.sand_mine.domain.Zone
import com.leti.sand_mine.repository.ZoneRepository

@RestController
@RequestMapping("api/shifts")
class ShiftController(
    private val shiftRepository: ShiftRepository,
    private val zoneRepository: ZoneRepository
) {
    @PostMapping("/create")
    fun createShift(@RequestBody shiftDto: ShiftDTO): ShiftDTO? {
        shiftDto.shiftId = null;
        val zone: Zone = zoneRepository.findById(shiftDto.zoneId!!).get();
        val shift: Shift = Shift(shiftDto.shiftId, shiftDto.date, shiftDto.attended, zone);
        return ShiftDTO(shiftRepository.save(shift))
    }


}