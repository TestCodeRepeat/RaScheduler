package com.rascalventures.app.rascheduler

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.ai.claude.opus.MainScreenOpus
import com.openai.chatgpt.GptDeliverySlotPickerScreen
import com.rascalventures.app.rascheduler.ui.RaSchedulerScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column {
            RaSchedulerScreen()
            GptDeliverySlotPickerScreen()
        }
//        MainScreenOpus()
    }
}