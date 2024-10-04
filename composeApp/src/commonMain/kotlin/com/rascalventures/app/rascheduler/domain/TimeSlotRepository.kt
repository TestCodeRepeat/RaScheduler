package com.rascalventures.app.rascheduler.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.openai.chatgpt.model.GptTimeSlotType
import com.rascalventures.app.rascheduler.domain.DateUtils.isSecondFridayFirstSlotUnavailable
import com.rascalventures.app.rascheduler.domain.model.DateSlot
import com.rascalventures.app.rascheduler.domain.model.DateSlotGroup
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

    fun selectSlot(date: LocalDate, slotType: TimeSlotType) {
        selectedSlot.value = Pair(date, slotType)
    }

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

