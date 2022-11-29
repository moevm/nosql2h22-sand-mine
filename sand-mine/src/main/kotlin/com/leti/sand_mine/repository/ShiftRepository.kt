package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.Shift
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate


interface ShiftRepository : Neo4jRepository<Shift, Long> {

    @Query(
        "MATCH (shift: SHIFT)-[in:IN]->(zone:ZONE) WHERE shift.date >= \$dateFrom AND shift.date <= \$dateTo AND toLower(toString(shift.attended)) =~ \$attended AND toString(ID(zone)) =~ \$zoneIds " +
                "WITH shift, in, zone MATCH (worker: WORKER)-[has_shift:HAS_SHIFT]->(shift) WHERE ID(worker) = \$workerId " +
                "RETURN worker, has_shift, shift, in, zone " +
                "ORDER BY shift.date"
    )
    fun getFilteredShiftList(
        @Param("workerId") workerId: Long,
        @Param("dateFrom") dateFrom: LocalDate,
        @Param("dateTo") dateTo: LocalDate,
        @Param("attended") attended: String,
        @Param("zoneIds") zoneIds: String
    ): Set<Shift>

    @Query("MATCH (worker:WORKER)-[has_shift:HAS_SHIFT]->(shift:SHIFT)-[in:IN]->(zone:ZONE) where ID(worker)=\$workerId return shift,has_shift,worker,in,zone ORDER BY shift.date")
    fun getAllShiftsByWorker(workerId:Long):Set<Shift>
}