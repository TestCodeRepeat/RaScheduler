package com.rascalventures.app.rascheduler.domain

import com.openai.chatgpt.TimeslotProvider.isSecondFridayFirstSlotUnavailable
import com.openai.chatgpt.model.GptDateSlot
import com.rascalventures.app.rascheduler.domain.model.DateSlot
import com.rascalventures.app.rascheduler.domain.model.DateSlotGroup
import com.rascalventures.app.rascheduler.domain.model.TimeSlot
import com.rascalventures.app.rascheduler.domain.model.TimeSlotType
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class TimeSlotRepository {
    fun generateTimeSlots(
        weeks: Int = 4,
        groupSize: Int = 3,
        flag: Boolean = false
    ): List<DateSlotGroup> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val endDate = today.plus(weeks * 7, DateTimeUnit.DAY)

        val dateSlots = mutableListOf<DateSlot>()
        var currentDate = today

        while (currentDate < endDate) {
            if (currentDate.dayOfWeek != DayOfWeek.SUNDAY) {
                val isWednesdayUnavailable = flag && currentDate.dayOfWeek == DayOfWeek.WEDNESDAY
                val isSecondFridayFirstSlotUnavailable = isSecondFridayFirstSlotUnavailable(currentDate)

                val timeSlots = TimeSlotType.entries.map {
                    val isSlotAvailable = when {
                        isWednesdayUnavailable -> false
                        isSecondFridayFirstSlotUnavailable && it == TimeSlotType.AM -> false
                        else -> true
                    }
                    TimeSlot(type = it, isAvailable = isSlotAvailable)
                }
                val dateSlot = DateSlot(
                    date = currentDate,
                    timeSlots = timeSlots,
                    isAvailable = !isWednesdayUnavailable
                )

                dateSlots.add(dateSlot)
            }
            currentDate = currentDate.plus(1, DateTimeUnit.DAY)
        }

        val totalDates = dateSlots.size
        val filledGroupCount = totalDates / groupSize
        val totalNeededDates = filledGroupCount * groupSize
        val filledDateSlots = dateSlots.take(totalNeededDates)

        val dateSlotGroups = filledDateSlots.chunked(groupSize).mapIndexed { index, group ->
            DateSlotGroup(
                groupIndex = index,
                dateSlots = group
            )
        }

        return dateSlotGroups
    }
}