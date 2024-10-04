package com.ai.claude.opus

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ai.claude.opus.data.OpusSlotDaoImpl
import com.ai.claude.opus.domain.OpusSlotRepository
import com.ai.claude.opus.domain.model.OpusSlot
import com.ai.claude.opus.ui.DeliverySlotPicker

@Composable
fun MainScreenOpus() {

    val slotDao = OpusSlotDaoImpl()
    val slotRepository = OpusSlotRepository(slotDao)

    var selectedSlot by remember { mutableStateOf<OpusSlot?>(null) }

    Column {
        DeliverySlotPicker(
            excludeWednesdays = true,
            slotRepository = slotRepository,
            onSlotSelected = { slot ->
                selectedSlot = slot
            }
        )

        selectedSlot?.let { slot ->
            Text("Selected Slot: ${slot.date} ${slot.timeSlot}")
        }
    }
}

