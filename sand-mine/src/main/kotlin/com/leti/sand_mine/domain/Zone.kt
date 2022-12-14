package com.leti.sand_mine.domain

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Property

@Node("ZONE")
data class Zone(
    @Id @GeneratedValue val id: Long? = null,
    @Property(name = "name") val name: String,
)
