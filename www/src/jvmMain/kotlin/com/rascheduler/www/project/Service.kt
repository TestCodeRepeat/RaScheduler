package com.rascheduler.www.project

import com.rascheduler.shared.data.model.SelectedSlotResponse
import com.rascheduler.shared.domain.SharedRepository
import com.rascheduler.shared.domain.TimeSlotRepository
import com.rascheduler.shared.domain.model.DateGroup
import com.rascheduler.shared.domain.model.SelectedSlot
import org.koin.core.annotation.Factory

@Suppress("ACTUAL_WITHOUT_EXPECT")
@Factory
actual class PingService : IPingService {

    private val sharedRepository = SharedRepository()
    private val timeSlotRepository = TimeSlotRepository()

    private var slot: SelectedSlot? = null

    override suspend fun generateDateGroups(weeks: Int, groupSize: Int, flag: Boolean): List<DateGroup> =
        timeSlotRepository.generateDateGroups(weeks, groupSize, flag)

    override suspend fun updateSelectedSlot(slot: SelectedSlot): SelectedSlotResponse {
        this.slot = slot
        return fetchSelectedSlot()
    }


    override suspend fun fetchSelectedSlot(): SelectedSlotResponse {
        if (this.slot != null) {
            return SelectedSlotResponse.Success(this.slot!!)
        }
        return SelectedSlotResponse.Empty("no slot selected")
    }

    /**
     * Test functions / sanity check
     */
    override suspend fun generateTestList(): List<String> {
        return listOf("one", "two", "three")
    }

    override suspend fun ping(message: String): String {
        return "Hello world from server! -- your message: $message"
    }

    override suspend fun helloFromShared(): String {
        return sharedRepository.hello()
    }
}
