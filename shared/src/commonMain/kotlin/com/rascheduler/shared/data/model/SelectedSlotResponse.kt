package com.rascheduler.shared.data.model

import com.rascheduler.shared.domain.model.SelectedSlot

sealed class SelectedSlotResponse {
    data class Success(val selectedSlot: SelectedSlot) : SelectedSlotResponse()
    data object Empty : SelectedSlotResponse()
}