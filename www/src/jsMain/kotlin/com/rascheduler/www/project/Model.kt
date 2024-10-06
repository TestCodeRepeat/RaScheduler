package com.rascheduler.www.project

import com.rascheduler.shared.domain.TimeSlotRepository
import com.rascheduler.shared.domain.model.DateGroup
import io.kvision.remote.getService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object Model {

    val timeSlotRepository = TimeSlotRepository()
    var timeSlots: List<DateGroup> = emptyList()
    val selectedSlot = timeSlotRepository.getSelectedSlot()

    val _dateGroups =  MutableStateFlow(emptyList<DateGroup>())
    val dateGroups = _dateGroups.asStateFlow()

    private val pingService = getService<IPingService>()

    suspend fun basicClick(): String {
        print("Model.basicClick()")
        val testList = pingService.generateTestList()
        return testList.joinToString { it }
    }

    suspend fun getDateGroups(): List<DateGroup> {
        print("Model.getDateGroups()")
        val res = pingService.generateDateGroups(5, 31, true)
        print("res.size = ${res.size}\n")
        _dateGroups.value = res
        return res
    }

    suspend fun pingServer(message: String): String {
        return pingService.ping(message)
    }

}
