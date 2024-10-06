package com.rascheduler.www.project

import com.rascheduler.shared.domain.SharedRepository
import com.rascheduler.shared.domain.TimeSlotRepository
import com.rascheduler.shared.domain.model.DateGroup
import com.rascheduler.shared.domain.model.SelectedSlot
import org.koin.core.annotation.Factory

@Suppress("ACTUAL_WITHOUT_EXPECT")
@Factory
actual class PingService : IPingService {

    val sharedRepository = SharedRepository()
    val timeSlotRepository = TimeSlotRepository()

    var slot: SelectedSlot? = null

    override suspend fun generateTestList(): List<String> {
        return listOf("one", "two", "three")
    }

    override suspend fun generateDateGroups(weeks: Int, groupSize: Int, flag: Boolean): List<DateGroup> {
        print("PingService.generateDateGroups()")
        val res = timeSlotRepository.generateDateGroups(6, 4, flag)

        print("res.size = ${res.size}\n")
        return res
    }

    override suspend fun ping(message: String): String {
        println(message)
        return "Hello world from server! -- your message: $message :: ${timeSlotRepository.generateDateGroups().size}"
    }

    override suspend fun helloFromShared(): String {
        return sharedRepository.hello()
    }

    override suspend fun updateSelectedSlot(slot: SelectedSlot) {
        this.slot = slot
    }
}
