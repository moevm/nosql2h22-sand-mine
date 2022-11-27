package com.leti.sand_mine.controller

import com.leti.sand_mine.DTO.WorkerDTO
import com.leti.sand_mine.DTO.ZoneDto
import com.leti.sand_mine.domain.Zone
import com.leti.sand_mine.exceptions.NotFoundException
import com.leti.sand_mine.repository.ZoneRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/zone")
class ZoneController (
    private val zoneRepository: ZoneRepository
) {
    @GetMapping("/all")
    fun getAllZones(): Set<ZoneDto> {
        return zoneRepository.findAll()
            .filterNotNull()
            .mapNotNull { zone ->
                ZoneDto(
                    zone.id ?: -1,
                    zone.name
                )
            }
            .toSet()
    }

    @GetMapping("/{zoneId}")
    fun getZone(@PathVariable zoneId: Long): ZoneDto {
        val zone = zoneRepository
            .findByIdOrNull(zoneId)
            ?: throw NotFoundException()
        return ZoneDto.toDto(zone);
    }

    @PostMapping("/ids")
    fun getZonesByIds(@RequestBody zonesIds: List<Long>): List<ZoneDto> {
        println(zonesIds);
        return zoneRepository
            .findAllById(zonesIds).map { zone ->
                with(zone) {
                    ZoneDto.toDto(zone)
                }
            }
    }
}