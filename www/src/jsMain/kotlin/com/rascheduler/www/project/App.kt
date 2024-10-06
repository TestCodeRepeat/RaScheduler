package com.rascheduler.www.project

import com.rascheduler.shared.data.model.SelectedSlotResponse
import com.rascheduler.www.project.Model.appState
import com.rascheduler.www.project.Model.getDateGroups
import com.rascheduler.www.project.ui.dateGroupCell
import io.kvision.Application
import io.kvision.BootstrapIconsModule
import io.kvision.CoreModule
import io.kvision.DatetimeModule
import io.kvision.FontAwesomeModule
import io.kvision.MapsModule
import io.kvision.MaterialModule
import io.kvision.ToastifyModule
import io.kvision.core.AlignItems
import io.kvision.core.ListStyle
import io.kvision.core.ListStyleType
import io.kvision.html.button
import io.kvision.html.h3
import io.kvision.html.ul
import io.kvision.module
import io.kvision.panel.VPanel
import io.kvision.panel.root
import io.kvision.startApplication
import io.kvision.state.bind
import io.kvision.utils.pc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

val AppScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

class App : Application() {

    override fun start(state: Map<String, Any>) {
        val root = root("kvapp") {
            AppScope.launch {
                getDateGroups()
            }
        }
        root.add(
            VPanel {
                alignItems = AlignItems.CENTER
                width = 100.pc
            }.bind(appState) { appState ->
                when (val selected = appState.selectedSlot) {
                    is SelectedSlotResponse.Empty -> {
                        h3 { +"Selected Time Slot: Empty" }
                    }

                    is SelectedSlotResponse.Success -> {
                        h3 { +"Selected Time Slot: ${selected.slot.date} - ${selected.slot.timeSlotType}" }
                    }
                }

                button("Refresh Data Groups") {
                    width = 25.pc
                    onClick {
                        AppScope.launch {
                            val res = getDateGroups()
                            println("Model.getDateGroups() returned: ${res.first().dateSlots.first().date}")
                        }
                    }
                }

                ul {
                    listStyle = ListStyle(ListStyleType.NONE)
                    appState.dateGroups.forEach { dateGroup ->
                        dateGroupCell(dateGroup, Model::onTimeSelectTimeSlotClicked, Model::isSelected)
                    }
                }
            }
        )
    }
}

fun main() {
    startApplication(
        ::App,
        module.hot,
        DatetimeModule,
        ToastifyModule,
        FontAwesomeModule,
        BootstrapIconsModule,
        MapsModule,
        MaterialModule,
        CoreModule
    )
}
