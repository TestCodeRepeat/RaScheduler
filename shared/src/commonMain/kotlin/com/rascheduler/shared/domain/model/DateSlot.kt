package com.rascheduler.shared.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class DateSlot(
    @Contextual
    val date: LocalDate,
    val timeSlots: List<TimeSlot>,
    var isAvailable: Boolean = true
)