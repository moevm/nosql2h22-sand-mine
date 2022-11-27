package com.leti.sand_mine.DTO

import com.leti.sand_mine.domain.Worker
import com.leti.sand_mine.domain.Zone

data class ZoneDto(
    val zoneId: Long?,
    val name: String
) {
    companion object {
        fun toDto(zone: Zone): ZoneDto {
            with(zone) {
                return ZoneDto(
                    id,
                    name
                )
            }
        }
    }
}