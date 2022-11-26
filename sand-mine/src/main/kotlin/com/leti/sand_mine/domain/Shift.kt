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
import org.springframework.data.neo4j.core.support.DateString
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime
import org.neo4j.driver.internal.value.DateTimeValue
import org.neo4j.driver.internal.value.DateValue
import org.springframework.format.annotation.DateTimeFormat

@Node("SHIFT")
data class Shift(
    @Id @GeneratedValue val id: Long?,
    @Property(name = "date")  val date: DateValue,
    @Property(name = "attended") val attended: Boolean,
    @Relationship(type = "IN", direction = Relationship.Direction.OUTGOING)
    var zone: Zone
) {
}