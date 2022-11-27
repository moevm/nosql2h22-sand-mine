package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.Shift
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate


interface ShiftRepository : Neo4jRepository<Shift,Long> {

    @Query(
        "MATCH (shift: SHIFT)-[in:IN]->(zone:ZONE) WHERE shift.date >= \$dateFrom AND shift.date <= \$dateTo AND shift.attended IN \$attended AND ID(zone) IN \$zoneIds " +
        "WITH shift, in, zone MATCH (worker: WORKER)-[has_shift:HAS_SHIFT]->(shift) WHERE ID(worker) = \$workerId " +
        "RETURN worker, has_shift, shift, in, zone"
    )
    fun getFilteredShiftList(@Param("workerId") workerId: Long, @Param("dateFrom") dateFrom: LocalDate, @Param("dateTo") dateTo: LocalDate, @Param("attended") attended: List<Boolean>, @Param("zoneIds") zoneIds: List<Long>) : Set<Shift>


    @Query(
        "MATCH (shift: SHIFT)-[in:IN]->(zone:ZONE) WHERE shift.date >= \$dateFrom AND shift.date <= \$dateTo AND shift.attended IN \$attended " +
        "WITH shift, in, zone MATCH (worker: WORKER)-[has_shift:HAS_SHIFT]->(shift) WHERE ID(worker) = \$workerId " +
        "RETURN worker, has_shift, shift, in, zone"
    )
    fun getFilteredByShiftShiftList(@Param("workerId") workerId: Long, @Param("dateFrom") dateFrom: LocalDate, @Param("dateTo") dateTo: LocalDate, @Param("attended") attended: List<Boolean>) : Set<Shift>
}