package com.rascheduler.www.project

import com.rascheduler.www.project.Model.basicClick
import com.rascheduler.www.project.Model.getDateGroups
import com.rascheduler.www.project.Model.timeSlots
import io.kvision.Application
import io.kvision.BootstrapIconsModule
import io.kvision.CoreModule
import io.kvision.DatetimeModule
import io.kvision.FontAwesomeModule
import io.kvision.MapsModule
import io.kvision.MaterialModule
import io.kvision.ToastifyModule
import io.kvision.core.AlignItems
import io.kvision.html.button
import io.kvision.html.h2
import io.kvision.html.h3
import io.kvision.module
import io.kvision.panel.VPanel
import io.kvision.panel.root
import io.kvision.startApplication
import io.kvision.state.observableListOf
import io.kvision.types.toDateF
import io.kvision.types.toStringF
import io.kvision.utils.pc
import io.kvision.utils.px
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import shared.SharedModel

val AppScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

class App : Application() {

    override fun start(state: Map<String, Any>) {
        val root = root("kvapp") {
        }
        val sharedModel = SharedModel("Hello", 42)
        root.add(
            VPanel {
                alignItems = AlignItems.CENTER
                width = 40.pc
                h2 { +"Hello, ${sharedModel.name} - ${sharedModel.number}  KVision!" }
                h3 { +"Time slots size ---- : ${timeSlots.size}" }

                h3 { +"Selected Time Slot: ${Model.selectedSlot}" }
                button("Click Me") {
                    marginBottom = 50.px
                    width = 25.pc
                    onClick {
                        AppScope.launch {
                            val res = basicClick()
                            println("basicClick() returned: $res")
                        }

                    }
                }

                button("Data Groups") {
                    width = 25.pc
                    onClick {
                        AppScope.launch {
                            val res = getDateGroups()
                            println("Model.getDateGroups() returned: ${res.first().dateSlots.first().date}")
                        }
                    }
                }
            }
        )
    }
}


//@Serializable
//data class Book(val title: String, val author: String, val year: Int, val rating: Int)
//
//val model = listOf(
//    Book("Fairy tales", "Hans Christian Andersen", 1836, 4),
//    Book("Don Quijote De La Mancha", "Miguel de Cervantes", 1610, 4),
//    Book("Crime and Punishment", "Fyodor Dostoevsky", 1866, 3),
//    Book("In Search of Lost Time", "Marcel Proust", 1920, 5)
//)
//
//val testData = observableListOf("One", "Two", "Three")

val data = observableListOf(
    Employee(
        "Tiger Nixon",
        "System Architect",
        "Edinburgh",
        false,
        "2011-04-25".toDateF("YYYY-MM-DD").toStringF("YYYY-MM-DD"),
        320800
    ),
    Employee(
        "Garrett Winters",
        "Accountant",
        "Tokyo",
        true,
        "2011-07-25".toDateF("YYYY-MM-DD").toStringF("YYYY-MM-DD"),
        170750
    ),
    Employee(
        "Ashton Cox",
        "Junior Technical Author",
        "San Francisco",
        true,
        "2009-01-12".toDateF("YYYY-MM-DD").toStringF("YYYY-MM-DD"),
        86000
    )
)

@Serializable
data class Employee(
    val name: String?,
    val position: String?,
    val office: String?,
    val active: Boolean = false,
    val startDate: String?,
    val salary: Int?,
    val id: Int = counter++
) {
    companion object {
        internal var counter = 0
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
