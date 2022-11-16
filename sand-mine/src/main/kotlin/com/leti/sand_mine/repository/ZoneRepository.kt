package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.Zone
import org.springframework.data.neo4j.repository.Neo4jRepository

interface ZoneRepository : Neo4jRepository<Zone, Long>