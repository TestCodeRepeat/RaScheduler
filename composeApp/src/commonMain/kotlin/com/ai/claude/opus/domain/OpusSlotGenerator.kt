package com.ai.claude.opus.domain

import com.ai.claude.opus.domain.model.OpusSlot
import com.ai.claude.opus.domain.model.OpusTimeSlot
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

// Domain Layer

class OpusSlotGenerator(private val numberOfWeeks: Int = 4, private val groupSize: Int = 3) {
    fun generate(excludeWednesdays: Boolean): List<List<OpusSlot>> {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val slots = mutableListOf<List<OpusSlot>>()

        var date = currentDate
        while (slots.size < numberOfWeeks * 7 / groupSize) {
            val group = mutableListOf<OpusSlot>()
            repeat(groupSize) {
                if (date.dayOfWeek != DayOfWeek.SUNDAY && !(excludeWednesdays && date.dayOfWeek == DayOfWeek.WEDNESDAY)) {
                    group.addAll(generateSlotsForDate(date))
                }
                date = date.plus(1, DateTimeUnit.DAY)
            }
            if (group.isNotEmpty()) slots.add(group)
        }

        return slots
    }

    private fun generateSlotsForDate(date: LocalDate): List<OpusSlot> {
        return listOf(
            OpusSlot(date, OpusTimeSlot.AM, isSlotAvailable(date, OpusTimeSlot.AM)),
            OpusSlot(date, OpusTimeSlot.PM, isSlotAvailable(date, OpusTimeSlot.PM)),
            OpusSlot(date, OpusTimeSlot.EVE, isSlotAvailable(date, OpusTimeSlot.EVE))
        )
    }

    private fun isSlotAvailable(date: LocalDate, timeSlot: OpusTimeSlot): Boolean {
        if (timeSlot == OpusTimeSlot.AM && date.dayOfWeek == DayOfWeek.FRIDAY && date.dayOfMonth % 2 == 0) {
            return false
        }
        // Check database for slot availability
        return true // Placeholder, replace with actual database check
    }
}