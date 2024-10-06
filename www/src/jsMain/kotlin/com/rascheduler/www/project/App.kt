package com.rascheduler.www.project

import com.rascheduler.shared.domain.TimeSlotRepository
import com.rascheduler.shared.domain.model.SelectedSlot
import com.rascheduler.www.project.Model.basicClick
import com.rascheduler.www.project.Model.timeSlotRepository
import com.rascheduler.www.project.Model.timeSlots
import io.kvision.Application
import io.kvision.CoreModule
import io.kvision.DatetimeModule
import io.kvision.ToastifyModule
import io.kvision.FontAwesomeModule
import io.kvision.BootstrapIconsModule
import io.kvision.MapsModule
import io.kvision.MaterialModule
import io.kvision.core.onClick
import io.kvision.html.button
import io.kvision.html.h2
import io.kvision.html.h3
import io.kvision.module
import io.kvision.panel.HPanel
import io.kvision.panel.VPanel
import io.kvision.panel.hPanel
import io.kvision.panel.root
import io.kvision.startApplication
import io.kvision.types.LocalDate
import io.kvision.types.LocalDateTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import shared.SharedModel

val AppScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

class App : Application() {

    override fun start(state: Map<String, Any>) {
        val root = root("kvapp") {
        }
        val sharedModel = SharedModel("Hello", 42)
        root.add(
            VPanel {
                h2 { +"Hello, ${sharedModel.name} - ${sharedModel.number}  KVision!" }
                h3 { +"Time slots size ---- : ${timeSlots.size}" }

                h3 { +"Selected Time Slot: ${Model.selectedSlot}" }
                button("Click Me") {
                    onClick {
                        println("Button clicked!")
                        AppScope.launch {
                            println("AppScope.launch - Button clicked!")
                            val res = basicClick()
                            println("basicClick() returned: $res")
                        }

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
