package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUiTests {
    @get:Rule
    val composeRule = createComposeRule()
    @Test
    fun calculateTip_20PercentNoRoundUp(){
        composeRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }
        composeRule.onNodeWithText("Bill Amount")
            .performTextInput("100")
        composeRule.onNodeWithText("Tip Percentage")
            .performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(20/100*100)
        composeRule.onNodeWithText("Tip Amount: $expectedTip").assertExists("No node with this text was found.")

    }
}