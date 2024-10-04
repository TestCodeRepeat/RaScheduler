package com.rascalventures.app.rascheduler.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.rascalventures.app.rascheduler.domain.TimeSlotRepository
import com.rascalventures.app.rascheduler.domain.model.DateGroup

@Composable
fun RaSchedulerScreen() {
    val timeSlotRepository = TimeSlotRepository()
    val dateGroups = timeSlotRepository.generateDateGroups(groupSize = 5)

    fun isSelected():Boolean{
        return false
    }

    LazyColumn {
        dateGroups.forEach { dateGroup: DateGroup ->
            item {
                Row {
                    DateGroupCell(dateGroup, ::isSelected)
                }
            }
        }
    }

}
