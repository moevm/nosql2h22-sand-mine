package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.Shift
import org.springframework.data.neo4j.repository.Neo4jRepository

interface ShiftRepository : Neo4jRepository<Shift,Long>