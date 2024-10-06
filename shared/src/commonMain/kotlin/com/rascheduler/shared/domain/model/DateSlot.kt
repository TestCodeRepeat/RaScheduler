package com.rascheduler.shared.domain.model

import kotlinx.datetime.LocalDate

data class DateSlot(
    val date: LocalDate,
    val timeSlots: List<TimeSlot>,
    var isAvailable: Boolean = true
)