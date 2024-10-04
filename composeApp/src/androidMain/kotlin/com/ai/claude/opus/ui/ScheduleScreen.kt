package com.ai.claude.opus.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ai.claude.opus.domain.OpusSlotGenerator
import com.ai.claude.opus.domain.model.OpusSlot
import com.rascalventures.app.rascheduler.domain.DateUtils.formatToMonthDay


@Composable
fun DeliverySlotPicker(excludeWednesdays: Boolean) {
    val slotGenerator = OpusSlotGenerator()
    val slots by remember { mutableStateOf(slotGenerator.generate(excludeWednesdays)) }

    // LazyRow to display date groups
    LazyRow {
        items(slots) { dateSlots ->
            // Column to display slots for each date
            Column {
                dateSlots.forEach { slot ->
                    SlotItem(slot)
                }
            }
        }
    }
}

@Composable
fun SlotItem(slot: OpusSlot) {
    val backgroundColor = if (slot.available) Color.Green else Color.Gray
    val textColor = if (slot.available) Color.White else Color.LightGray

    // Box to represent a slot item
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(backgroundColor)
            .clickable { /* Handle slot selection */ }
    ) {
        Text(
            text = "${slot.date.formatToMonthDay()} ${slot.timeSlot}",
            color = textColor,
            modifier = Modifier.padding(16.dp)
        )
    }
}