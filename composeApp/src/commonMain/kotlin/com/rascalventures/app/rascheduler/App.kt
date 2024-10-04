package com.rascalventures.app.rascheduler

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ai.claude.opus.MainScreenOpus
import com.openai.chatgpt.GptDeliverySlotPickerScreen
import com.rascalventures.app.rascheduler.ui.RaSchedulerScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column() {
            RaSchedulerScreen()
//            GptDeliverySlotPickerScreen()
        }
//        MainScreenOpus()
    }
}