package com.openai.chatgpt.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.openai.chatgpt.model.TimeSlot

@Composable
fun SlotCell(
    timeSlot: TimeSlot,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(
                color = when {
                    isSelected -> MaterialTheme.colors.primary
                    !timeSlot.isAvailable -> MaterialTheme.colors.error
                    else -> MaterialTheme.colors.primary.copy(alpha = 0.1f)
                }
            )
            .clickable(enabled = timeSlot.isAvailable, onClick = onClick)
            .padding(8.dp)
    ) {
        Text(
            text = timeSlot.type.label,
            style = MaterialTheme.typography.body1,
            color = if (!timeSlot.isAvailable) MaterialTheme.colors.onError else MaterialTheme.colors.onBackground
        )
    }
}