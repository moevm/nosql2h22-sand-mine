package static.js.pages

import java.time.LocalDate

class AllShiftsFilterDTO(
    val fullName: String? = null,
    val phone: String? = null,
    val role: String? = null,
    val zones: List<Long>? = null,
    val dateFrom: LocalDate? = null,
    val dateTo: LocalDate? = null
)