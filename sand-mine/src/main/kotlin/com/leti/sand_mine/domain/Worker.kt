package com.leti.sand_mine.domain

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node
data class Worker(
    @Id
    @GeneratedValue
    val id: Long? = null
)
