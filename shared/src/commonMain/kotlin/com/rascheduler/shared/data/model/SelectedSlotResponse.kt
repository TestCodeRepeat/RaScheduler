package com.rascheduler.shared.data.model

import com.rascheduler.shared.domain.model.SelectedSlot
import kotlinx.serialization.Serializable

@Serializable
sealed class SelectedSlotResponse {
    @Serializable
    data class Success(val slot: SelectedSlot) : SelectedSlotResponse()
    @Serializable
    data class Empty(val message:String) : SelectedSlotResponse()
}