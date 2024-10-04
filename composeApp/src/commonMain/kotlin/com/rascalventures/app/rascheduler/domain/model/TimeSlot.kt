package com.rascalventures.app.rascheduler.domain.model

class TimeSlot(
    val type: TimeSlotType,
    var isAvailable: Boolean = true,
    var isSelected: Boolean = false
)

