package com.rascheduler.shared.domain.model

data class DateGroup(
    val groupIndex: Int,
    val dateSlots: List<DateSlot>
)