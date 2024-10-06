package com.rascheduler.shared.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class DateGroup(
    val groupIndex: Int,
    val dateSlots: List<DateSlot>
)