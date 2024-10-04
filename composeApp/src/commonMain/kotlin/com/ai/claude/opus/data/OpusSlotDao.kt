package com.ai.claude.opus.data

import com.ai.claude.opus.domain.model.OpusSlot

interface OpusSlotDao {
    fun getAllSlots(): List<OpusSlot>
    fun updateSlot(slot: OpusSlot)
}

