package com.ai.claude.opus.domain

import com.ai.claude.opus.data.OpusSlotDao
import com.ai.claude.opus.domain.model.OpusSlot

class OpusSlotRepository(private val slotDao: OpusSlotDao) {
    fun getSlots(excludeWednesdays: Boolean): List<List<OpusSlot>> {
        val allSlots = slotDao.getAllSlots()
        val slotGenerator = OpusSlotGenerator()
        val generatedSlots = slotGenerator.generate(excludeWednesdays)

        return generatedSlots.map { dateSlots ->
            dateSlots.map { slot ->
                allSlots.find { it.date == slot.date && it.timeSlot == slot.timeSlot } ?: slot
            }
        }
    }

    fun updateSlot(slot: OpusSlot) {
        slotDao.updateSlot(slot)
    }
}