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
    fun `no group should include a sunday`() = runTest() {
        val groups: List<DateSlotGroup> = timeSlotRepository.generateTimeSlots(groupSize = 3)
        groups.forEach { group ->
            group.dateSlots.forEach { dateSlot: DateSlot ->
                dateSlot shouldNotBe DayOfWeek.SUNDAY
            }
        }
    }

    @Test
    fun `each group should have 6 date slots`() = runTest() {
        val groups = timeSlotRepository.generateTimeSlots(groupSize = 6)
        groups.forEach {
            it.dateSlots.size shouldBe 6
        }
    }

    @Test
    fun `should return a list of date slot groups`() = runTest() {
        val res = timeSlotRepository.generateTimeSlots()
        res.size shouldBeGreaterThan 0
    }
}