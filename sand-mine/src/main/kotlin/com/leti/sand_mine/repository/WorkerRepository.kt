package com.leti.sand_mine.repository

import com.leti.sand_mine.domain.Worker
import org.springframework.data.neo4j.repository.Neo4jRepository

interface WorkerRepository : Neo4jRepository<Worker, Long>