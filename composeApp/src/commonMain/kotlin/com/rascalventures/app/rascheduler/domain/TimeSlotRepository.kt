package com.rascalventures.app.rascheduler.domain

import androidx.compose.runtime.mutableStateOf
import com.rascalventures.app.rascheduler.domain.DateUtils.isSecondFridayOfTheMonth
import com.rascalventures.app.rascheduler.domain.model.DateSlot
import com.rascalventures.app.rascheduler.domain.model.DateGroup
import com.rascalventures.app.rascheduler.domain.model.TimeSlot
import com.rascalventures.app.rascheduler.domain.model.TimeSlotType
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class TimeSlotRepository {

    var selectedSlot = mutableStateOf<Pair<LocalDate, TimeSlotType>?>(null)

    fun selectSlot(date: LocalDate, timeSlot: TimeSlot) {
        selectedSlot.value = Pair(date, timeSlot.type)
    }

    fun isTimeSlotSelected(date: LocalDate, slotType: TimeSlot): Boolean {
        val selected = selectedSlot.value
        return selected != null && selected.first == date && selected.second == slotType.type
    }

    fun generateDateGroups(
        weeks: Int = 4,
        groupSize: Int = 3,
        flag: Boolean = false
    ): List<DateGroup> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val endDate = today.plus(weeks * 7, DateTimeUnit.DAY)

        val dateSlots = mutableListOf<DateSlot>()
        var currentDate = today

        while (currentDate < endDate) {
            if (currentDate.dayOfWeek != DayOfWeek.SUNDAY) {
                val isWednesdayUnavailable = flag && currentDate.dayOfWeek == DayOfWeek.WEDNESDAY
                val isSecondFridayFirstSlotUnavailable = isSecondFridayOfTheMonth(currentDate)

                val timeSlots = TimeSlotType.entries.map {
                    val isSlotAvailable = when {
                        isWednesdayUnavailable -> false
                        isSecondFridayFirstSlotUnavailable && it == TimeSlotType.AM -> false
                        else -> true
                    }
                    TimeSlot(type = it, isAvailable = isSlotAvailable)
                }
                dateSlots.add(
                    DateSlot(
                        date = currentDate,
                        timeSlots = timeSlots,
                        isAvailable = !isWednesdayUnavailable
                    )
                )
            }
            currentDate = currentDate.plus(1, DateTimeUnit.DAY)
        }

        val totalDates = dateSlots.size
        val filledGroupCount = totalDates / groupSize
        val totalNeededDates = filledGroupCount * groupSize
        val filledDateSlots = dateSlots.take(totalNeededDates)

        val dateGroups = filledDateSlots.chunked(groupSize).mapIndexed { index, group ->
            DateGroup(
                groupIndex = index,
                dateSlots = group
            )
        }

        return dateGroups
    }
}

