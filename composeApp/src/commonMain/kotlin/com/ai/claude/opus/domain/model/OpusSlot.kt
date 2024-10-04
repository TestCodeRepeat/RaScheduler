package com.ai.claude.opus.domain.model

import kotlinx.datetime.LocalDate

data class OpusSlot(val date: LocalDate, val timeSlot: OpusTimeSlot, val available: Boolean)


