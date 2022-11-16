package com.leti.sand_mine.controller

import com.leti.sand_mine.repository.ZoneRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/zone")
class ZoneController (
    private val zoneRepository: ZoneRepository
) {
}