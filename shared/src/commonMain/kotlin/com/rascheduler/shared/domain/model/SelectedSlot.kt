package com.rascheduler.shared.domain.model

import kotlinx.datetime.LocalDate

data class SelectedSlot(val date: LocalDate, val timeSlotType: TimeSlotType)