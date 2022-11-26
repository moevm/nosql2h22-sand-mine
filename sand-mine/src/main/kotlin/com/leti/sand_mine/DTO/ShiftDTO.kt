package com.leti.sand_mine.DTO

import com.leti.sand_mine.domain.Shift
import java.time.LocalDate

class ShiftDTO(
    val shiftId: Long?,
    val date: LocalDate,
    val attended: Boolean,
    val zoneId: Long
) {
    constructor(shift: Shift) : this(shift.id, shift.date.asLocalDate(), shift.attended, shift.zone.id ?: -1)
}