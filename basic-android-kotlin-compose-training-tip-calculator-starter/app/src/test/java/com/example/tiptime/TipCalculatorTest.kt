package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTest {
    @Test
    fun calculateTip_20PercentNoRoundUp() {
        val amount = 100.00
        val tipPercent = 20.00
        val tip = tipPercent / 100 * amount
        val expectedTip = NumberFormat.getCurrencyInstance().format(tip)
        val actualTip = calculateTip(amount, tipPercent)

        assertEquals(expectedTip, actualTip)
    }
}