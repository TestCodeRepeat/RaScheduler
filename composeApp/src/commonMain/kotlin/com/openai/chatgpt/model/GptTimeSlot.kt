package com.openai.chatgpt.model

data class GptTimeSlot(
    val type: GptTimeSlotType,
    var isAvailable: Boolean = true,
    var isSelected: Boolean = false
)