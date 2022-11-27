package com.leti.sand_mine.controller

import com.leti.sand_mine.DTO.ZoneDto
import com.leti.sand_mine.repository.ZoneRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/zone")
class ZoneController (
    private val zoneRepository: ZoneRepository
) {
    @GetMapping("/all")
    fun getAllZones() : Set<ZoneDto> {
        return zoneRepository.findAll()
            .filterNotNull()
            .map { zone ->
                ZoneDto(
                    zone.id ?: -1,
                    zone.name
                )
            }
            .toSet()
    }
}