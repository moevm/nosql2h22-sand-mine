package com.leti.sand_mine.controller

import com.leti.sand_mine.DTO.MineStatsDTO
import com.leti.sand_mine.domain.MineStats
import com.leti.sand_mine.domain.Worker
import com.leti.sand_mine.domain.Zone
import com.leti.sand_mine.exceptions.NotFoundException
import com.leti.sand_mine.repository.MineStatsRepository
import com.leti.sand_mine.repository.WorkerRepository
import com.leti.sand_mine.repository.ZoneRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/mine_stats")
class MineStatsController(
    private val mineStatsRepository: MineStatsRepository,
    private val zoneRepository: ZoneRepository,
    private val workerRepository: WorkerRepository
) {
    @GetMapping("/all")
    fun getMineStatsAll(): List<MineStatsDTO> {
        return mineStatsRepository.findAll().map {mineStats->
            val editorId = mineStats.lastEditedBy.id
            if (editorId == null) {
                println("Error: Mine Stats was edited by worker with non-existing id")
                throw NotFoundException()
            }

            MineStatsDTO(
                id=mineStats.id,
                editorId=editorId,
                date=mineStats.date.asLocalDateTime(),
                weight=mineStats.weight.toFloat(),
                lastEditTime=mineStats.lastEditTime.asLocalDateTime()
            )
        }
    }
//
//    @GetMapping("/{id}")
//    fun getMineStats(@PathVariable id: Long): MineStatsDTO? {
//        val mineStats = mineStatsRepository.findByIdOrNull(id)
//        if (mineStats == null) {
//            println("Error: MineStats with id $id not found")
//            throw NotFoundException()
//        }
//
//        val editorId = mineStats.lastEditedBy.id
//        if (editorId == null) {
//            println("Error: Mine Stats was edited by worker with non-existing id")
//            throw NotFoundException()
//        }
//
//        return MineStatsDTO(
//            id=mineStats.id,
//            editorId=editorId,
//            date=mineStats.date,
//            weight=mineStats.weight,
//            lastEditTime=mineStats.lastEditTime
//        )
//    }
//
//    @PostMapping("/create")
//    fun createMineStats(@RequestBody mineStatsDTO: MineStatsDTO): MineStatsDTO? {
//        val zone: Zone? = zoneRepository.findByIdOrNull(mineStatsDTO.zoneId)
//        if (zone == null) {
//            println("Error: Zone with id ${mineStatsDTO.zoneId} not found")
//            return null
//        }
//
//        val editor: Worker? = workerRepository.findByIdOrNull(mineStatsDTO.editorId)
//        if (editor == null) {
//            println("Error: Worker with id ${mineStatsDTO.editorId} not found")
//            return null
//        }
//
//        val mineStats: MineStats = mineStatsRepository.save(MineStats(
//            date=mineStatsDTO.date,
//            weight=mineStatsDTO.weight,
//            lastEditTime=mineStatsDTO.lastEditTime,
//            lastEditedBy=editor
//        ))
//        zone.stats.plus(mineStats)
//        zoneRepository.save(zone)
//
//        mineStatsDTO.id = mineStats.id
//        return mineStatsDTO
//    }
}