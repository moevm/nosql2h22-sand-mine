package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.MineStats
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query

interface MineStatsRepository : Neo4jRepository<MineStats, Long>{
    fun findAllByIdIn(id: MutableCollection<Long>)
}