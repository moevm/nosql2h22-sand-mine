package com.leti.sand_mine.DTO

import com.leti.sand_mine.domain.Shift
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import java.time.LocalDateTime

class ShiftDTO(
    var shiftId: Long? = null,
    var date: LocalDateTime? = null,
    var attended: Boolean? = null,
    var zoneId: Long? = null
) {
    constructor(shift: Shift) : this(shift.id, shift.date, shift.attended, shift.zone?.id)
}