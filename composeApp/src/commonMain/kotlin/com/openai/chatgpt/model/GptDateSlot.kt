package com.openai.chatgpt.model

import kotlinx.datetime.LocalDate

data class GptDateSlot(
    val date: LocalDate,
    val timeSlots: List<GptTimeSlot>,
    var isAvailable: Boolean = true
)