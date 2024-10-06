package com.rascheduler.shared.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class SelectedSlot(val date: LocalDate, val timeSlotType: TimeSlotType)