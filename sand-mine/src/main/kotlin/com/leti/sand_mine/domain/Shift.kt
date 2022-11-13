package com.leti.sand_mine.domain

import lombok.Builder
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import org.jetbrains.annotations.NotNull
import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Property
import org.springframework.data.neo4j.core.schema.Relationship
import java.time.LocalDateTime

@Node("Shift")
@EqualsAndHashCode
data class Shift(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @NotNull @Property(name="date") var date:LocalDateTime?,
    @NotNull @Property(name="attended") var attended:Boolean?,
    @Relationship(type = "IN", direction = Relationship.Direction.OUTGOING)
    var zone:Zone? = null
){
}