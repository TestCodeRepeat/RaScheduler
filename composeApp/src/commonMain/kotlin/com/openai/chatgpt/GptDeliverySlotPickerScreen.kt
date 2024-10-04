package com.openai.chatgpt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.openai.chatgpt.TimeslotProvider.applyFullSlots
import com.openai.chatgpt.TimeslotProvider.fullSlots
import com.openai.chatgpt.TimeslotProvider.generateDateSlots
import com.openai.chatgpt.ui.DateSlotPicker

@Composable
fun GptDeliverySlotPickerScreen() {
    val groupSize = 4 // Configurable group size
    val flag = true // Flag indicating special item in order

    // Generate date slots
    var dateSlotGroups = remember {
        generateDateSlots(weeks = 4, groupSize = groupSize, flag = flag)
    }

    // Apply full slots
    dateSlotGroups = applyFullSlots(dateSlotGroups, fullSlots)

    // Render the picker
    DateSlotPicker(dateSlotGroups = dateSlotGroups)
}