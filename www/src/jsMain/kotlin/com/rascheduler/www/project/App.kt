package com.rascheduler.www.project

import com.rascheduler.shared.domain.TimeSlotRepository
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
import io.kvision.html.h2
import io.kvision.html.h3
import io.kvision.module
import io.kvision.panel.HPanel
import io.kvision.panel.VPanel
import io.kvision.panel.root
import io.kvision.startApplication
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
        AppScope.launch {
            val sharedModel = SharedModel("Hello", 42)
            val pingResult = Model.pingServer("boom!")

            root.add(
                VPanel {
                    h2 { +"Hello, ${sharedModel.name} - ${sharedModel.number}  KVision!" }
                    h3 { +"Ping result here ---- : $pingResult" }
                    h3 { +"Time slots size ---- : ${timeSlots.size}" }

                    HPanel {
                        h3 { +"Selected Time Slot: ${Model.selectedSlot}" }
                    }


                }
            )
        }
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
