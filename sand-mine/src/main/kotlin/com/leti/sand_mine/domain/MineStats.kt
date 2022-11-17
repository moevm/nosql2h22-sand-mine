package com.leti.sand_mine.domain

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Property
import org.springframework.data.neo4j.core.schema.Relationship
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Node("MineStats")
data class MineStats(
    @Id @GeneratedValue var id: Long,
    @Property(name="date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) var date: LocalDateTime,
    @Property(name="weight") var weight: Float,
    @Property(name="last_edit_time") var lastEditTime: LocalDateTime,

    @Relationship(type="LAST_EDITED", direction= Relationship.Direction.OUTGOING) var lastEditedBy: Worker,
)
