package com.rascheduler.shared.data.model

import com.rascheduler.shared.domain.model.SelectedSlot
import kotlinx.serialization.Serializable

@Serializable
sealed class SelectedSlotResponse {
    data class Success(val selectedSlot: SelectedSlot) : SelectedSlotResponse()
    data object Empty : SelectedSlotResponse()
}