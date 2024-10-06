package com.rascheduler.www.project

import com.rascheduler.shared.data.model.SelectedSlotResponse
import com.rascheduler.shared.domain.TimeSlotRepository
import com.rascheduler.shared.domain.model.DateGroup
import com.rascheduler.shared.domain.model.SelectedSlot
import com.rascheduler.shared.domain.model.TimeSlot
import io.kvision.remote.getService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

data class AppState(
    val selectedSlot: SelectedSlotResponse,
    val dateGroups: List<DateGroup>
)

object Model {

    private val _appState = MutableStateFlow(AppState(SelectedSlotResponse.Empty("-"), emptyList()))
    val appState = _appState.asStateFlow()

    private val pingService = getService<IPingService>()

    suspend fun getDateGroups(): List<DateGroup> {
        val res = pingService.generateDateGroups(15, 7, true)
        _appState.value = appState.value.copy(dateGroups = res)
        return res
    }

    fun onTimeSelectTimeSlotClicked(date: LocalDate, timeSlot: TimeSlot) {
        AppScope.launch {
            val res = pingService.updateSelectedSlot(SelectedSlot(date, timeSlot.type))
            _appState.value = appState.value.copy(selectedSlot = res)
        }
    }

    fun isSelected(date: LocalDate, timeSlot: TimeSlot): Boolean {
        return when (val selected = appState.value.selectedSlot) {
            is SelectedSlotResponse.Empty -> false
            is SelectedSlotResponse.Success -> {
                selected.slot.date == date && selected.slot.timeSlotType == timeSlot.type
            }
        }
    }

    suspend fun getSelectedSlot() {
        _appState.value = appState.value.copy(selectedSlot = pingService.fetchSelectedSlot())
    }
}
