package com.openai.chatgpt.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.openai.chatgpt.model.GptDateSlot
import com.openai.chatgpt.model.GptTimeSlotType
import com.rascheduler.shared.util.DateUtils.formatToMonthDay
import kotlinx.datetime.LocalDate


@Composable
internal fun GptSlotGroupItem(
    dateSlots: List<GptDateSlot>,
    selectedSlot: Pair<LocalDate, GptTimeSlotType>?,
    onSlotSelected: (LocalDate, GptTimeSlotType) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, MaterialTheme.colors.primary),
        ) {
        Row(modifier = Modifier.background(Color.Magenta)) {
            dateSlots.forEach { dateSlot ->
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = dateSlot.date.formatToMonthDay(),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
        GptTimeSlotType.entries.forEach { timeSlotType ->
            Row() {
                dateSlots.forEach { dateSlot ->
                    val timeSlot = dateSlot.timeSlots.find { it.type == timeSlotType }!!
                    val isSelected =
                        selectedSlot?.first == dateSlot.date && selectedSlot.second == timeSlotType
                    GptSlotCell(
                        timeSlot = timeSlot,
                        isSelected = isSelected,
                        onClick = {
                            if (timeSlot.isAvailable) {
                                onSlotSelected(dateSlot.date, timeSlotType)
                            }
                        }
                    )
                }
            }
        }
    }
}