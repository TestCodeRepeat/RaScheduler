package com.openai.chatgpt.model

import kotlinx.datetime.LocalDate

internal data class GptDateSlot(
    val date: LocalDate,
    val timeSlots: List<GptTimeSlot>,
    var isAvailable: Boolean = true
)