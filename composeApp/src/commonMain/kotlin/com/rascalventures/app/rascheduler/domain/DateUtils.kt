package com.rascalventures.app.rascheduler.domain

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
}