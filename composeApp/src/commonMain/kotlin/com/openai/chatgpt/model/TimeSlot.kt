package com.openai.chatgpt.model

data class TimeSlot(
    val type: TimeSlotType,
    var isAvailable: Boolean = true,
    var isSelected: Boolean = false
)