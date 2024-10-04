package com.openai.chatgpt

import com.openai.chatgpt.model.DateSlot
import com.openai.chatgpt.model.TimeSlot
import com.openai.chatgpt.model.TimeSlotType
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

object TimeslotProvider {

    // Simulated full slots
    val fullSlots = listOf(
        Pair(LocalDate(2024, 10, 5), TimeSlotType.PM),
        Pair(LocalDate(2024, 10, 6), TimeSlotType.EVE)
    )

    // Apply full slots to date slots
    fun applyFullSlots(
        dateSlots: List<List<DateSlot>>,
        fullSlots: List<Pair<LocalDate, TimeSlotType>>
    ): List<List<DateSlot>> {
        dateSlots.flatten().forEach { dateSlot ->
            fullSlots.forEach { (fullDate, fullSlotType) ->
                if (dateSlot.date == fullDate) {
                    dateSlot.timeSlots.find { it.type == fullSlotType }?.isAvailable = false
                }
            }
        }
        return dateSlots
    }

    fun generateDateSlots(
        weeks: Int = 4,
        groupSize: Int = 3,
        flag: Boolean = false
    ): List<List<DateSlot>> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val endDate = today.plus(weeks * 7, DateTimeUnit.DAY)

        val dateSlots = mutableListOf<DateSlot>()
        var currentDate = today

        while (currentDate < endDate) {
            // Exclude Sundays
            if (currentDate.dayOfWeek != DayOfWeek.SUNDAY) {
                val isWednesdayUnavailable = flag && currentDate.dayOfWeek == DayOfWeek.WEDNESDAY
                val isSecondFridayFirstSlotUnavailable = isSecondFridayFirstSlotUnavailable(currentDate)

                val timeSlots = TimeSlotType.values().map { timeSlotType ->
                    val isSlotAvailable = when {
                        isWednesdayUnavailable -> false
                        isSecondFridayFirstSlotUnavailable && timeSlotType == TimeSlotType.AM -> false
                        else -> true
                    }
                    TimeSlot(type = timeSlotType, isAvailable = isSlotAvailable)
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

        // Ensure groups are filled to capacity
        val totalDates = dateSlots.size
        val filledGroupCount = totalDates / groupSize
        val totalNeededDates = filledGroupCount * groupSize
        val filledDateSlots = dateSlots.take(totalNeededDates)

        // Group dates
        return filledDateSlots.chunked(groupSize)
    }

    fun isSecondFridayFirstSlotUnavailable(date: LocalDate): Boolean {
        if (date.dayOfWeek != DayOfWeek.FRIDAY) return false

        // Determine the week of the month
        val dayOfMonth = date.dayOfMonth
        val weekOfMonth = ((dayOfMonth - 1) / 7) + 1

        // Check if it's every second Friday
        return weekOfMonth % 2 == 0
    }

}

fun LocalDate.formatToMonthDay(): String {
    val dayOfWeek = this.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
    val month = this.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
    val day = this.dayOfMonth
    return "$dayOfWeek $day"
}