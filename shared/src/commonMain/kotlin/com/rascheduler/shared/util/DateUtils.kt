package com.rascheduler.shared.util

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate

object DateUtils {
    fun isSecondFridayOfTheMonth(date: LocalDate): Boolean {
        if (date.dayOfWeek != DayOfWeek.FRIDAY) return false

        // Determine the week of the month
        val dayOfMonth = date.dayOfMonth
        val weekOfMonth = ((dayOfMonth - 1) / 7) + 1

        // Check if it's every second Friday
        return weekOfMonth % 2 == 0
    }

    fun LocalDate.formatToMonthDay(): String {
        val dayOfWeek = this.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
        val month = this.month.name.lowercase().replaceFirstChar { it.uppercase() }.take(3)
        val dayOfMonth = this.dayOfMonth
        return "$dayOfWeek $dayOfMonth"
    }

    fun LocalDate.formatLongMonth(): String {
        val dayOfWeek = this.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        val month = this.month.name.lowercase().replaceFirstChar { it.uppercase() }
        val dayOfMonth = this.dayOfMonth
        return "$dayOfWeek, $month $dayOfMonth"
    }
}