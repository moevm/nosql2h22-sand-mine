package com.leti.sand_mine.DTO

import com.leti.sand_mine.domain.MineStats
import com.leti.sand_mine.domain.Worker
import java.time.LocalDate
import java.time.LocalDateTime

data class MineStatsDTO(
    var id: Long? = null,
    val zoneId: Long? = null,
    val editorId: Long,
    val date: LocalDate,
    val weight: Double,
    val lastEditTime: LocalDateTime
) {
}