package com.rascalventures.app.rascheduler.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rascalventures.app.rascheduler.domain.DateUtils.formatToMonthDay
import com.rascalventures.app.rascheduler.domain.model.DateGroup
import com.rascalventures.app.rascheduler.domain.model.DateSlot
import com.rascalventures.app.rascheduler.domain.model.TimeSlot
import kotlinx.datetime.LocalDate

@Composable
fun DateGroupCell(
    dateGroup: DateGroup,
    isSelected: (LocalDate, TimeSlot) -> Boolean,
    onSelectTimeSlotClicked: (LocalDate, TimeSlot) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        val first = dateGroup.dateSlots.first()
        Text("${first.date.month.name} - Week ${dateGroup.groupIndex + 1}")
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
            dateGroup.dateSlots.forEach { dateSlot: DateSlot ->
                DateColumnCell(
                    dateSlot = dateSlot,
                    onSelectTimeSlotClicked = onSelectTimeSlotClicked,
                    isSelected = isSelected
                )
            }
        }
    }

}

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

