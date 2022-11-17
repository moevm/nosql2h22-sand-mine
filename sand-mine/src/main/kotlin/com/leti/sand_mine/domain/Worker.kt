package com.leti.sand_mine.domain

import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Property
import org.springframework.data.neo4j.core.schema.Relationship

@Node("Worker")
data class Worker(
    @Id @GeneratedValue var id: Long,
    @Property(name="surname") var surname: String,
    @Property(name="name") var name: String,
    @Property(name="patronymic") var patronymic: String,
    @Property(name="email") var email: String,
    @Property(name="phone_number") var phoneNumber: String,
    @Property(name="passport") var passport: String,
    @Property(name="role") var role: String,
    @Property(name="pass_id") var pass_id: Long,
    @Property(name="password") var password: String,

    @Relationship(type="HAS_ACCESS_TO", direction=Relationship.Direction.OUTGOING) var zonesWithAccess: Set<Zone>,
    @Relationship(type="HAS_SHIFT", direction=Relationship.Direction.OUTGOING) var shifts: MutableList<Shift>
)
