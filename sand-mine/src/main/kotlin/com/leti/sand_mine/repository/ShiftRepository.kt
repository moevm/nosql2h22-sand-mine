package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.Shift
import com.leti.sand_mine.domain.Worker
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

    @Query(
        "MATCH(worker: WORKER)-[has_shift: HAS_SHIFT]->(shift: SHIFT)-[in:IN]->(zone:ZONE) WHERE toLower(worker.name) =~ toLower(\$name) AND toLower(worker.surname) =~ toLower(\$surname) " +
                "AND toLower(worker.patronymic) =~ toLower(\$patronymic) AND worker.phone_number =~ \$phone AND worker.role =~ \$role " +
                "AND shift.date >= \$dateFrom AND shift.date <= \$dateTo AND toString(ID(zone)) =~ \$zoneIds RETURN  worker, has_shift, shift, in, zone "
    )
    fun allShiftsFilter(
        @Param("name") name: String,
        @Param("surname") surname: String,
        @Param("patronymic") patronymic: String,
        @Param("phone") phone: String,
        @Param("role") role: String,
        @Param("dateFrom") dateFrom: LocalDate,
        @Param("dateTo") dateTo: LocalDate,
        @Param("zoneIds") zoneIds: String
    ): List<Shift>

    @Query("MATCH (worker:WORKER)-[has_shift:HAS_SHIFT]->(shift:SHIFT)-[in:IN]->(zone:ZONE) where ID(worker)=\$workerId return shift,has_shift,worker,in,zone ORDER BY shift.date")
    fun getAllShiftsByWorker(workerId: Long): Set<Shift>
}