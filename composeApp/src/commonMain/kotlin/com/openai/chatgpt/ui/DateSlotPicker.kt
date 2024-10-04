package com.openai.chatgpt.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.openai.chatgpt.model.DateSlot
import com.openai.chatgpt.model.TimeSlotType
import kotlinx.datetime.LocalDate

@Composable
fun DateSlotPicker(dateSlotGroups: List<List<DateSlot>>) {
    var selectedSlot by remember { mutableStateOf<Pair<LocalDate, TimeSlotType>?>(null) }

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(dateSlotGroups) { group ->
            SlotGroup(
                dateSlots = group,
                selectedSlot = selectedSlot,
                onSlotSelected = { date, slotType ->
                    selectedSlot = Pair(date, slotType)
                }
            )
        }
    }
}

