package com.rascalventures.app.rascheduler.domain.model

data class DateSlotGroup(
    val groupIndex: Int,
    val dateSlots: List<DateSlot>
)