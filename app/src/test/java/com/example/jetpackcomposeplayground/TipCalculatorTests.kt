package com.example.jetpackcomposeplayground
import com.example.jetpackcomposeplayground.ui.screens.calculateTip
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat


class TipCalculatorTests {

    @Test
    fun calculateTip_20PercentNoRoundupTwoPerson() {
        val amount = 100.00
        val tipPercent = 20.00
        val tipPerson = 2
        val expectidTip = NumberFormat.getCurrencyInstance().format(10)
        val actualTip = calculateTip(amount,tipPercent,tipPerson,false)
        assertEquals(expectidTip,actualTip)
    }

    @Test
    fun calculateTip_20PercentNoRoundupThreePerson() {
        val amount = 100.00
        val tipPercent = 20.20
        val tipPerson = 3
        val expectidTip = NumberFormat.getCurrencyInstance().format(7)
        val actualTip = calculateTip(amount,tipPercent,tipPerson,true)
        assertEquals(expectidTip,actualTip)
    }

    @Test
    fun calculateTip_20PercentNoRoundupNoPerson() {
        val amount = 100.00
        val tipPercent = 20.20
        val tipPerson = 0
        val expectidTip = NumberFormat.getCurrencyInstance().format(21)
        val actualTip = calculateTip(amount,tipPercent,tipPerson,true)
        assertEquals(expectidTip,actualTip)
    }

}