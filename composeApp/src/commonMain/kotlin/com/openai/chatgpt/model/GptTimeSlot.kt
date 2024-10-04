package com.openai.chatgpt.model

internal data class GptTimeSlot(
    val type: GptTimeSlotType,
    var isAvailable: Boolean = true,
    var isSelected: Boolean = false
)