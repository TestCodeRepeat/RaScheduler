package com.rascalventures.app.rascheduler.domain.model

import com.openai.chatgpt.model.GptDateSlot
import kotlinx.datetime.LocalDate

data class DateSlot(
    val date: LocalDate,
    val timeSlots: List<TimeSlot>,
    var isAvailable: Boolean = true
)