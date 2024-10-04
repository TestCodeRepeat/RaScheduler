package com.rascalventures.app.rascheduler.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rascalventures.app.rascheduler.domain.DateUtils.formatToMonthDay
import com.rascalventures.app.rascheduler.domain.model.DateSlot
import com.rascalventures.app.rascheduler.domain.model.TimeSlot
import kotlinx.datetime.LocalDate

@Composable
fun DateColumnCell(
    dateSlot: DateSlot,
    isSelected: (LocalDate, TimeSlot) -> Boolean,
    onSelectTimeSlotClicked: (LocalDate, TimeSlot) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        Text(dateSlot.date.formatToMonthDay())
        dateSlot.timeSlots.forEach { timeSlot ->
            TimeSlotCell(
                timeSlot = timeSlot,
                onClick = {
                    onSelectTimeSlotClicked(dateSlot.date, timeSlot)
                },
                isSelected = {
                    isSelected(dateSlot.date, timeSlot)
                })
        }

    }


}