package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.MineStats
import com.leti.sand_mine.domain.Zone
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query

interface ZoneRepository : Neo4jRepository<Zone, Long> {
}
