package com.leti.sand_mine.domain

import org.neo4j.driver.internal.value.DateValue
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Property
import org.springframework.data.neo4j.core.schema.Relationship

@Node("SHIFT")
data class Shift(
    @Id @GeneratedValue var id: Long? = null,
    @Property(name = "date") var date: DateValue,
    @Property(name = "attended") var attended: Boolean?,
    @Relationship(type = "IN", direction = Relationship.Direction.OUTGOING)
    var zone: Zone? = null
) {
}