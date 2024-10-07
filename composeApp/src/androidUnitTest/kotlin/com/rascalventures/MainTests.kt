package com.rascalventures

import com.rascheduler.shared.util.DateUtils.isSecondFridayOfTheMonth
import com.rascheduler.shared.domain.TimeSlotRepository
import com.rascheduler.shared.domain.model.DateSlot
import com.rascheduler.shared.domain.model.DateGroup
import com.rascheduler.shared.domain.model.SelectedSlot
import com.rascheduler.shared.domain.model.TimeSlotType
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate

class MainTests {

    private val timeSlotRepository = TimeSlotRepository()

    @Test
    fun `wednesday should be marked as unavailable`() = runTest() {
        val dateGroups = timeSlotRepository.generateDateGroups(flag = true)
        dateGroups.forEach { dateGroup ->
            dateGroup.dateSlots.forEach { dateSlot ->
                if (dateSlot.date.dayOfWeek == DayOfWeek.WEDNESDAY) {
                    dateSlot.isAvailable shouldBe false
                    dateSlot.timeSlots.forEach { timeSlot ->
                        timeSlot.isAvailable shouldBe false
                    }
                }
            }
        }
    }

    @Test
    fun `should be true when a date is the second friday of the month`() = runTest() {
        isSecondFridayOfTheMonth(LocalDate(2024, 10, 11)) shouldBe true
        isSecondFridayOfTheMonth(LocalDate(2024, 10, 18)) shouldBe false
    }

    @Test
    fun `ensure second friday is unavailable`() = runTest() {
        val dateGroups = timeSlotRepository.generateDateGroups()
        val secondFridayOctober = LocalDate(2024, 10, 11)
        val allFridays = dateGroups.map { dateGroup ->
            dateGroup.dateSlots.map { dateSlot -> dateSlot }
        }.flatten().filter { it.date.dayOfWeek == DayOfWeek.FRIDAY }

        val matchingFriday = allFridays.find { it.date == secondFridayOctober }
        matchingFriday!!.timeSlots
            .first { it.type == TimeSlotType.AM }
            .isAvailable shouldBe false

        matchingFriday.timeSlots
            .first { it.type == TimeSlotType.PM }
            .isAvailable shouldBe true
    }

    @Test
    fun `should mark a time slot as selected`() = runTest() {
        val dateGroups: List<DateGroup> = timeSlotRepository.generateDateGroups()
        val selectedDate = dateGroups.first().dateSlots.first()
        val selectedSlot = selectedDate.timeSlots[1]
        timeSlotRepository.selectSlot(selectedDate.date, selectedSlot)
        timeSlotRepository.slot.value shouldBe SelectedSlot(selectedDate.date, selectedSlot.type)
    }

    @Test
    fun `no group should include a sunday`() = runTest() {
        timeSlotRepository.generateDateGroups(groupSize = 4)
            .forEach { group: DateGroup ->
                group.dateSlots.forEach { dateSlot: DateSlot ->
                    dateSlot shouldNotBe DayOfWeek.SUNDAY
                }
            }
    }

    @Test
    fun `should handle groups of 6 and 2 date slots`() = runTest() {
        timeSlotRepository.generateDateGroups(groupSize = 6)
            .forEach { dateSlotGroup ->
                dateSlotGroup.dateSlots.size shouldBe 6
            }

        timeSlotRepository.generateDateGroups(groupSize = 2)
            .forEach {
                it.dateSlots.size shouldBe 2
            }
    }

    @Test
    fun `should return a list of date slot groups`() = runTest() {
        val dateSlotGroups = timeSlotRepository.generateDateGroups()
        dateSlotGroups.size shouldBeGreaterThan 0
    }
}