package com.rascalventures.app.rascheduler.domain.model

import com.openai.chatgpt.model.GptTimeSlotType

class TimeSlot(
    val type: GptTimeSlotType,
    var isAvailable: Boolean = true,
    var isSelected: Boolean = false
)