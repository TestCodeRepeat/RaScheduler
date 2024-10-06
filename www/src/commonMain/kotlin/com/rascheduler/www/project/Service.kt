package com.rascheduler.www.project

import com.rascheduler.shared.data.model.SelectedSlotResponse
import com.rascheduler.shared.domain.model.DateGroup
import com.rascheduler.shared.domain.model.SelectedSlot
import io.kvision.annotations.KVService

@KVService
interface IPingService {
    suspend fun ping(message: String): String
    suspend fun helloFromShared(): String
    suspend fun updateSelectedSlot(slot: SelectedSlot): SelectedSlotResponse
    suspend fun generateDateGroups(weeks: Int, groupSize: Int, flag: Boolean): List<DateGroup>
    suspend fun generateTestList(): List<String>
    suspend fun fetchSelectedSlot(): SelectedSlotResponse
}

