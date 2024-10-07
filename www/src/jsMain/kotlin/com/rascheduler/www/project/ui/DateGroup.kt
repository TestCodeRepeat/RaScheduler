package com.rascheduler.www.project.ui

import com.rascheduler.shared.domain.model.DateGroup
import com.rascheduler.shared.domain.model.TimeSlot
import com.rascheduler.shared.util.DateUtils.formatToMonthDay
import io.kvision.core.FontStyle
import io.kvision.core.FontWeight
import io.kvision.core.onClick
import io.kvision.html.Align
import io.kvision.html.Ul
import io.kvision.html.h4
import io.kvision.html.label
import io.kvision.html.li
import io.kvision.html.table
import io.kvision.html.td
import io.kvision.html.th
import io.kvision.html.tr
import io.kvision.panel.hPanel
import kotlinx.datetime.LocalDate

fun Ul.dateGroupCell(
    group: DateGroup,
    onSelectTimeSlotClicked: (LocalDate, TimeSlot) -> Unit,
    isSelected: (LocalDate, TimeSlot) -> Boolean
) {
    li {
        h4 {
            align = Align.CENTER
            fontStyle = FontStyle.NORMAL
            fontWeight = FontWeight.LIGHTER
            +"BATCH # ${group.groupIndex + 1}"
        }
        hPanel {
            group.dateSlots.forEach { dateSlot ->
                table {
                    tr {
                        th {
                            label { +dateSlot.date.formatToMonthDay() }
                        }
                    }
                    dateSlot.timeSlots.forEach { timeSlot ->
                        tr {
                            val className = when {
                                !timeSlot.isAvailable -> "slot-square-unavailable"
                                isSelected(dateSlot.date, timeSlot) -> "slot-square-selected"
                                else -> "slot-square"
                            }
                            td(className = className) { +timeSlot.type.name }
                            onClick {
                                if (timeSlot.isAvailable) {
                                    onSelectTimeSlotClicked(dateSlot.date, timeSlot)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}