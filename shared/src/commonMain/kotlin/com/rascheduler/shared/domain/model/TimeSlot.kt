package com.rascheduler.shared.domain.model

class TimeSlot(
    val type: TimeSlotType,
    var isAvailable: Boolean = true,
    var isSelected: Boolean = false // TODO - set the isSelected at Repository level, rather than passing isSelected() function
)

