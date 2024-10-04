package com.ai.claude.opus.data

import com.ai.claude.opus.domain.model.OpusSlot

class OpusSlotDaoImpl : OpusSlotDao {
    private val slots = mutableListOf<OpusSlot>()

    override fun getAllSlots(): List<OpusSlot> {
        return slots
    }

    override fun updateSlot(slot: OpusSlot) {
        slots.indexOfFirst { it.date == slot.date && it.timeSlot == slot.timeSlot }.let { index ->
            if (index != -1) {
                slots[index] = slot
            } else {
                slots.add(slot)
            }
        }
    }
}