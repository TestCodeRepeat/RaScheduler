package com.rascalventures.app.rascheduler.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.openai.chatgpt.formatToMonthDay
import com.rascalventures.app.rascheduler.domain.model.DateGroup
import com.rascalventures.app.rascheduler.domain.model.DateSlot
import com.rascalventures.app.rascheduler.domain.model.TimeSlotType

@Composable
fun DateGroupCell(dateGroup: DateGroup, isSelected: () -> Boolean) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Date Group ${dateGroup.groupIndex}")
        Row {
            dateGroup.dateSlots.forEach { dateSlot: DateSlot ->
                DateSlotCell(dateSlot, isSelected)
            }
        }
    }

}

@Composable
fun DateSlotCell(dateSlot: DateSlot, isSelected: () -> Boolean) {
    Column {
        Text(dateSlot.date.formatToMonthDay())
        dateSlot.timeSlots.forEach {
            TimeSlotCell(it, isSelected)
        }

    }


}

