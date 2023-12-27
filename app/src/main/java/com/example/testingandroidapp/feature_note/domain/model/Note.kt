package com.example.testingandroidapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testingandroidapp.ui.theme.BabyBlue
import com.example.testingandroidapp.ui.theme.LightGreen
import com.example.testingandroidapp.ui.theme.RedOrange
import com.example.testingandroidapp.ui.theme.RedPink
import com.example.testingandroidapp.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
){
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink )
    }
}

class InvalidNoteException( message: String): Exception(message)
