package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.Worker
import org.neo4j.driver.internal.value.DateValue
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface WorkerRepository : Neo4jRepository<Worker, Long> {

    @Query(
        "MATCH (worker: WORKER {id: {workerId}}-[:HAS_SHIFT]->" +
                "(shift: SHIFT) where shift.date > {from} AND shift.date < {to} RETURN shift"
    )
    fun getShiftsByPeriod(workerId: Long, from: DateValue, to: DateValue)

    @Query("MATCH(worker:WORKER) WHERE worker.phone_number = \$login OR worker.email = \$login RETURN worker")
    fun findByLogin(@Param("login") login: String): Worker?

    fun findByPassId(@Param("passId") passId: Long): Worker?

    fun findAllByRole(role:String):Set<Worker>
    fun findAllByIdIn(id: List<Long>): Set<Worker>

    @Query(
        "MATCH(worker: WORKER)-[has_shift: HAS_SHIFT]->(shift: SHIFT)-[in:IN]->(zone:ZONE) WHERE worker.name =~ \$name AND worker.surname =~ \$surname " +
                "AND worker.patronymic =~ \$patronymic AND worker.phone_number =~ \$phone AND worker.role =~ \$role " +
                "AND shift.date >= \$dateFrom AND shift.date <= \$dateTo AND toString(ID(zone)) =~ \$zoneIds RETURN worker, has_shift, shift, in, zone"
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
    ): List<Worker>

    @Query("MATCH(worker: WORKER) RETURN DISTINCT worker.role")
    fun findAllRoles(): Set<String>

    @Query("MATCH(worker: WORKER)-[has_access:HAS_ACCESS_TO]->(zone: ZONE) WHERE worker.surname =~ \$surname AND worker.name =~ \$name AND worker.patronymic =~ \$patronymic AND " +
           "worker.phone_number =~ \$phoneNumber AND toString(worker.role) =~ \$roles AND toString(ID(zone)) =~ \$zones " +
           "RETURN worker, has_access, zone")
    fun getFilteredWorkersList(@Param("surname") surname: String, @Param("name") name: String, @Param("patronymic") patronymic: String, @Param("phoneNumber") phoneNumber: String, @Param("roles") roles: String, @Param("zones") zones: String): List<Worker>
}