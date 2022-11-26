package com.leti.sand_mine.DTO

import com.leti.sand_mine.domain.Shift
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import org.neo4j.driver.internal.value.DateTimeValue
import org.neo4j.driver.internal.value.DateValue
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime

class ShiftDTO(
    val shiftId: Long?,
    val date: LocalDate,
    val attended: Boolean,
    val zoneId: Long
) {
    constructor(shift: Shift) : this(shift.id, shift.date.asLocalDate(), shift.attended, shift.zone.id)
}