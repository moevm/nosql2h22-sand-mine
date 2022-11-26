package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.Worker
import org.neo4j.driver.internal.value.DateValue
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.data.repository.query.Param

interface WorkerRepository : Neo4jRepository<Worker, Long> {

    @Query("MATCH (worker: WORKER {id: {workerId}}-[:HAS_SHIFT]->" +
            "(shift: SHIFT) where shift.date > {from} AND shift.date < {to} RETURN shift")
    fun getShiftsByPeriod(workerId: Long, from: DateValue, to: DateValue)

    @Query("MATCH(worker:WORKER) WHERE worker.phone_number = \$login OR worker.email = \$login RETURN worker")
    fun findByLogin(@Param("login") login: String) : Worker?
}