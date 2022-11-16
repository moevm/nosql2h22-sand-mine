package com.leti.sand_mine.domain

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Property
import org.springframework.data.neo4j.core.schema.Relationship

@Node("Zone")
data class Zone(
    @Id @GeneratedValue val id: Long,
    @Property(name = "name") val name: String,
    @Relationship(type = "HAS_STATS", direction = Relationship.Direction.OUTGOING) val stats: List<MineStats>
)
