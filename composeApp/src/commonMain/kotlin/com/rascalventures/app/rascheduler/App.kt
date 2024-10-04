package com.rascalventures.app.rascheduler

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.ai.claude.opus.MainScreenOpus
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
//        DeliverySlotPickerScreen()
        MainScreenOpus()
    }
}