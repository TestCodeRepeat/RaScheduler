package com.rascheduler.shared.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class TimeSlotType(val label: String) {
    AM("AM"),
    PM("PM"),
    EVE("EVE")
}