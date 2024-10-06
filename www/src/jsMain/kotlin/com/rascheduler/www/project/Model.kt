package com.rascheduler.www.project

import com.rascheduler.shared.domain.TimeSlotRepository
import io.kvision.remote.getService

object Model {

    val timeSlotRepository = TimeSlotRepository()
    val timeSlots = timeSlotRepository.generateDateGroups(3, 4)
    val selectedSlot = timeSlotRepository.getSelectedSlot()

    private val pingService = getService<IPingService>()

    suspend fun pingServer(message: String): String {
        return pingService.ping(message)
    }

}
