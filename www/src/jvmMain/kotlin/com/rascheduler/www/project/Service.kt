package com.rascheduler.www.project

import com.rascheduler.shared.domain.SharedRepository
import com.rascheduler.shared.domain.TimeSlotRepository
import com.rascheduler.shared.domain.model.SelectedSlot
import org.koin.core.annotation.Factory

@Suppress("ACTUAL_WITHOUT_EXPECT")
@Factory
actual class PingService : IPingService {

    val sharedRepository = SharedRepository()
    val timeSlotRepository = TimeSlotRepository()

    override suspend fun ping(message: String): String {
        println(message)
        return "Hello world from server! -- your message: $message :: ${timeSlotRepository.generateDateGroups().size}"
    }

    override suspend fun helloFromShared(): String {
        return sharedRepository.hello()
    }

    override suspend fun setSelectedSlot(slot: SelectedSlot) {

    }


}
