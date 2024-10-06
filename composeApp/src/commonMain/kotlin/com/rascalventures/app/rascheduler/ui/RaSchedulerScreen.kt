package com.rascalventures.app.rascheduler.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rascalventures.app.rascheduler.domain.DateUtils.formatLongMonth
import com.rascalventures.app.rascheduler.domain.TimeSlotRepository
import com.rascalventures.app.rascheduler.domain.model.DateGroup
import org.jetbrains.compose.ui.tooling.preview.Preview

const val GROUP_SIZE = 5
const val NUM_WEEKS = 8
const val FLAG = true

@Composable
fun RaSchedulerScreen() {
    val timeSlotRepository = TimeSlotRepository()
    val dateGroups =
        timeSlotRepository.generateDateGroups(
            weeks = NUM_WEEKS,
            groupSize = GROUP_SIZE,
            flag = FLAG
        )


    LazyColumn(modifier = Modifier.fillMaxWidth()) {

        item {
            Row(
                Modifier.padding(start = 16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    fontStyle = MaterialTheme.typography.h5.fontStyle,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    textAlign = TextAlign.Center,
                    text = "Selected Time Slot: \n${timeSlotRepository.selectedSlot.value?.first?.formatLongMonth() ?: "--"}"
                )
            }
        }

        dateGroups.forEach { dateGroup: DateGroup ->
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    DateGroupCell(
                        dateGroup = dateGroup,
                        onSelectTimeSlotClicked = timeSlotRepository::selectSlot,
                        isSelected = timeSlotRepository::isTimeSlotSelected
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun RaSchedulerScreenPreview() {
    RaSchedulerScreen()
}