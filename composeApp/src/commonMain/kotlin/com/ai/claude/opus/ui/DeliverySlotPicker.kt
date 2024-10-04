package com.ai.claude.opus.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ai.claude.opus.domain.OpusSlotRepository
import com.ai.claude.opus.domain.model.OpusSlot

@Composable
fun DeliverySlotPicker(
    excludeWednesdays: Boolean,
    slotRepository: OpusSlotRepository,
    onSlotSelected: (OpusSlot) -> Unit
) {
    val slots by remember { mutableStateOf(slotRepository.getSlots(excludeWednesdays)) }

    LazyColumn {
        items(slots) { dateSlots ->
            Row(modifier = Modifier.fillMaxWidth()) {
                dateSlots.forEach { slot ->
                    SlotItem(
                        slot = slot,
                        onSlotSelected = { selectedSlot ->
                            slotRepository.updateSlot(selectedSlot.copy(available = false))
                            onSlotSelected(selectedSlot)
                        }
                    )
                }
            }
        }
    }
}