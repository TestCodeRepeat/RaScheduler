package com.rascalventures

import com.rascalventures.app.rascheduler.domain.TimeSlotRepository
import com.rascalventures.app.rascheduler.domain.model.DateSlot
import com.rascalventures.app.rascheduler.domain.model.DateSlotGroup
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.DayOfWeek

class MainTests {

    val timeSlotRepository = TimeSlotRepository()

    @Test
    fun `should mark a time slot as selected`() = runTest() {
        val timeSlots = timeSlotRepository.generateTimeSlots()
        val selectedDate = timeSlots.first().dateSlots.first()
        val selectedSlot = selectedDate.timeSlots[1]
        timeSlotRepository.selectSlot(selectedDate.date, selectedSlot.type)
        timeSlotRepository.selectedSlot.value shouldBe Pair(selectedDate.date, selectedSlot.type)
    }

    @Test
    fun `no group should include a sunday`() = runTest() {
        timeSlotRepository.generateTimeSlots(groupSize = 3)
            .forEach { group: DateSlotGroup ->
                group.dateSlots.forEach { dateSlot: DateSlot ->
                    dateSlot shouldNotBe DayOfWeek.SUNDAY
                }
            }
    }

    @Test
    fun `should handle groups of 6 and 2 date slots`() = runTest() {
        timeSlotRepository.generateTimeSlots(groupSize = 6)
            .forEach { dateSlotGroup ->
                dateSlotGroup.dateSlots.size shouldBe 6
            }

        timeSlotRepository.generateTimeSlots(groupSize = 2)
            .forEach {
                it.dateSlots.size shouldBe 2
            }
    }

    @Test
    fun `should return a list of date slot groups`() = runTest() {
        val dateSlotGroups = timeSlotRepository.generateTimeSlots()
        dateSlotGroups.size shouldBeGreaterThan 0
    }
}