package com.rascalventures.app.rascheduler.domain.model

data class DateGroup(
    val groupIndex: Int,
    val dateSlots: List<DateSlot>
)