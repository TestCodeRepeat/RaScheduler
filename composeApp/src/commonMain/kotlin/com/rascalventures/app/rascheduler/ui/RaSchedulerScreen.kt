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
import com.rascheduler.shared.domain.FLAG
import com.rascheduler.shared.domain.GROUP_SIZE
import com.rascheduler.shared.domain.NUM_WEEKS
import com.rascheduler.shared.domain.SharedRepository
import com.rascheduler.shared.domain.TimeSlotRepository
import com.rascheduler.shared.domain.model.DateGroup
import com.rascheduler.shared.util.DateUtils.formatLongMonth
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RaSchedulerScreen() {
    val sharedRepository = SharedRepository()
    val timeSlotRepository = TimeSlotRepository()
    val dateGroups =
        timeSlotRepository.generateDateGroups(
            weeks = NUM_WEEKS,
            groupSize = GROUP_SIZE,
            flag = FLAG
        )


    LazyColumn(modifier = Modifier.fillMaxWidth()) {

        item { Text("${sharedRepository.hello()}- SharedModel = ${timeSlotRepository.sharedModel.name} - ${timeSlotRepository.sharedModel.number}") }
        item {
            Row(
                Modifier.padding(start = 16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    fontStyle = MaterialTheme.typography.h5.fontStyle,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    textAlign = TextAlign.Center,
                    text = "Selected Time Slot: \n${timeSlotRepository.selectedSlot.value?.date?.formatLongMonth() ?: "--"}"
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