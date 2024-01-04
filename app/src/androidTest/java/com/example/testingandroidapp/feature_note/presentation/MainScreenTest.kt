package com.example.testingandroidapp.feature_note.presentation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.testingandroidapp.ui.theme.NoteAppCleanArchMviMvvMTheme
import org.junit.Rule
import org.junit.Test

class MainScreenTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun mainScreenTest() {
        composeTestRule.activity.setContent {
            NoteAppCleanArchMviMvvMTheme {
                MainScreen()
            }
        }
        composeTestRule.onNodeWithText("Your Note").assertExists()
    }
    @Test
    fun navTestNoteEditScreen() {
        composeTestRule.activity.setContent {
            NoteAppCleanArchMviMvvMTheme {
                MainScreen()
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule.onNodeWithContentDescription("Add Note").performClick()
        composeTestRule.onNodeWithContentDescription("Save Note").assertExists()
    }
}