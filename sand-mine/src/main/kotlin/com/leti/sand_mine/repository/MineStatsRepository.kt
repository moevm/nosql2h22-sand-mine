package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.MineStats
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import java.time.LocalDateTime

interface MineStatsRepository : Neo4jRepository<MineStats, Long> {
    fun findAllByIdIn(id: MutableCollection<Long>)

    @Query(
        "MATCH (mine_stats:MINE_STATS)-[in_zone:IN_ZONE]->(zone:ZONE) " +
                "WITH mine_stats, in_zone, zone MATCH (worker:WORKER) <- [last_edited:LAST_EDITED] -(mine_stats) " +
                "RETURN mine_stats,in_zone,zone,worker,last_edited " +
                "ORDER BY mine_stats.date DESC,mine_stats.last_edit_time DESC"
    )
    fun findAllSortedByDate(): List<MineStats>

    @Query(
        "MATCH (mine_stats:MINE_STATS)-[in_zone:IN_ZONE]->(zone:ZONE) WHERE mine_stats.date >= \$dateFrom AND mine_stats.date <= \$dateTo " +
                "AND ( mine_stats.last_edit_time.epochSeconds >= \$timeEditStart AND mine_stats.last_edit_time.epochSeconds <= \$timeEditEnd) AND mine_stats.weight >= \$weightFrom AND mine_stats.weight <= \$weightTo " +
                "AND (ID(zone) IN \$zoneIds OR size(\$zoneIds) = 0)" +
                "WITH mine_stats, in_zone, zone MATCH (worker:WORKER) <- [last_edited:LAST_EDITED] - (mine_stats) WHERE (ID(worker) IN \$lastEditorIds OR size(\$lastEditorIds)=0)" +
                "RETURN mine_stats,in_zone,zone,worker,last_edited " +
                "ORDER BY mine_stats.date DESC,mine_stats.last_edit_time DESC"
    )
    fun getFilteredMineStats(
        @Param("timeEditStart") timeEditStart: Long,
        @Param("timeEditEnd") timeEditEnd: Long,
        @Param("dateFrom") dateFrom: LocalDate,
        @Param("dateTo") dateTo: LocalDate,
        @Param("lastEditorIds") lastEditorIds: List<Long>,
        @Param("weightFrom") weightFrom: Double,
        @Param("weightTo") weightTo: Double,
        @Param("zoneIds") zoneIds: List<Long>
    ): List<MineStats>
}