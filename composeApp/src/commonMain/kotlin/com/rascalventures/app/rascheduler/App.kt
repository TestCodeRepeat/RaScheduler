package com.rascalventures.app.rascheduler

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.ai.claude.opus.MainScreenOpus
import com.openai.chatgpt.GptDeliverySlotPickerScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        GptDeliverySlotPickerScreen()
//        MainScreenOpus()
    }
}