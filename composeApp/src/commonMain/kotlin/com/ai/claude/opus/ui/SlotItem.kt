package com.ai.claude.opus.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ai.claude.opus.domain.model.OpusSlot
import com.rascheduler.shared.domain.DateUtils.formatToMonthDay

@Composable
fun SlotItem(slot: OpusSlot, onSlotSelected: (OpusSlot) -> Unit) {
    val backgroundColor = if (slot.available) Color.Green else Color.Gray
    val textColor = if (slot.available) Color.White else Color.LightGray

    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(backgroundColor)
            .clickable {
                if (slot.available) {
                    onSlotSelected(slot)
                }
            }
    ) {
        Text(
            text = "${slot.date.formatToMonthDay()} ${slot.timeSlot}",
            color = textColor,
            modifier = Modifier.padding(16.dp)
        )
    }
}