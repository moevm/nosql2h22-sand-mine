package com.leti.sand_mine.domain

import org.neo4j.driver.internal.value.DateTimeValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Property
import org.springframework.data.neo4j.core.schema.Relationship
import org.neo4j.driver.internal.value.DateValue

@Node("MINE_STATS")
data class MineStats(
    @Id @GeneratedValue var id: Long? = null,
    @Property(name = "date") var date: DateValue,
    @Property(name = "weight") var weight: Double,
    @Property(name = "last_edit_time") var lastEditTime: DateTimeValue,

    @Relationship(type = "LAST_EDITED", direction = Relationship.Direction.OUTGOING) var lastEditedBy: Worker,
    @Relationship(type = "IN_ZONE", direction = Relationship.Direction.OUTGOING) val parentZone: Zone
)
