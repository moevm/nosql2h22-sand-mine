package com.leti.sand_mine.controller

import com.leti.sand_mine.DTO.MineStatsDTO
import com.leti.sand_mine.domain.MineStats
import com.leti.sand_mine.domain.Worker
import com.leti.sand_mine.domain.Zone
import com.leti.sand_mine.exceptions.NotFoundException
import com.leti.sand_mine.repository.MineStatsRepository
import com.leti.sand_mine.repository.WorkerRepository
import com.leti.sand_mine.repository.ZoneRepository
import org.neo4j.driver.internal.value.DateTimeValue
import org.neo4j.driver.internal.value.DateValue
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@RestController
@RequestMapping("api/mine_stats")
class MineStatsController(
    private val mineStatsRepository: MineStatsRepository,
    private val zoneRepository: ZoneRepository,
    private val workerRepository: WorkerRepository
) {
    @GetMapping("/all")
    fun getMineStatsAll(): List<MineStatsDTO> {
        return mineStatsRepository.findAll().map { mineStats ->
            val editorId = mineStats.lastEditedBy.id
            if (editorId == null) {
                println("Error: Mine Stats was edited by worker with non-existing id")
                throw NotFoundException()
            }

            MineStatsDTO(
                id = mineStats.id,
                zoneId = mineStats.parentZone.id,
                editorId = editorId,
                date = mineStats.date.asLocalDate(),
                weight = mineStats.weight,
                lastEditTime = mineStats.lastEditTime.asZonedDateTime().toLocalDateTime()
            )
        }
    }

    @GetMapping("/{id}")
    fun getMineStats(@PathVariable id: Long): MineStatsDTO {
        val mineStats = mineStatsRepository.findByIdOrNull(id)
        if (mineStats == null) {
            println("Error: MineStats with id $id not found")
            throw NotFoundException()
        }

        val editorId = mineStats.lastEditedBy.id
        if (editorId == null) {
            println("Error: Mine Stats was edited by worker with non-existing id")
            throw NotFoundException()
        }

        return MineStatsDTO(
            id = mineStats.id,
            zoneId = mineStats.parentZone.id,
            editorId = editorId,
            date = mineStats.date.asLocalDate(),
            weight = mineStats.weight,
            lastEditTime = mineStats.lastEditTime.asZonedDateTime().toLocalDateTime()
        )
    }

    @PostMapping("/create")
    fun createMineStats(@RequestBody mineStatsDTO: MineStatsDTO): MineStatsDTO {
        val zone: Zone? = zoneRepository.findByIdOrNull(mineStatsDTO.zoneId)
        if (zone == null) {
            println("Error: Zone with id ${mineStatsDTO.zoneId} not found")
            throw NotFoundException()
        }

        val editor: Worker? = workerRepository.findByIdOrNull(mineStatsDTO.editorId)
        if (editor == null) {
            println("Error: Worker with id ${mineStatsDTO.editorId} not found")
            throw NotFoundException()
        }

        val mineStats: MineStats = mineStatsRepository.save(
            MineStats(
                date = DateValue(mineStatsDTO.date),
                weight = mineStatsDTO.weight,
                lastEditTime = DateTimeValue(ZonedDateTime.of(mineStatsDTO.lastEditTime, ZoneId.systemDefault())),
                lastEditedBy = editor,
                parentZone = zone
            )
        )
        zoneRepository.save(zone)

        mineStatsDTO.id = mineStats.id
        return mineStatsDTO
    }
}