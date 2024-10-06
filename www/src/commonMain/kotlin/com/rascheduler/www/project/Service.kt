package com.rascheduler.www.project

import com.rascheduler.shared.domain.model.SelectedSlot
import io.kvision.annotations.KVService

@KVService
interface IPingService {
    suspend fun ping(message: String): String
    suspend fun helloFromShared(): String
    suspend fun setSelectedSlot(slot: SelectedSlot)
}
