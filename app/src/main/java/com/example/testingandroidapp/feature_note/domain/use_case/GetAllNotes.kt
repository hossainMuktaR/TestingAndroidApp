package com.example.testingandroidapp.feature_note.domain.use_case

import com.example.testingandroidapp.feature_note.domain.model.Note
import com.example.testingandroidapp.feature_note.domain.repository.NoteRepository
import com.example.testingandroidapp.feature_note.domain.utils.NoteOrder
import com.example.testingandroidapp.feature_note.domain.utils.OrderType

class GetAllNotes(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(
        noteOrder: NoteOrder
    ): List<Note> {
        val notes = repository.getAllNotes()
        return when (noteOrder.orderType) {
            is OrderType.Ascending -> {
                when (noteOrder) {
                    is NoteOrder.Title -> {
                        notes.sortedBy { it.title }
                    }

                    is NoteOrder.Date -> {
                        notes.sortedBy { it.timeStamp }
                    }

                    is NoteOrder.Color -> {
                        notes.sortedBy { it.color }
                    }
                }
            }

            is OrderType.Descending -> {
                when (noteOrder) {
                    is NoteOrder.Title -> {
                        notes.sortedByDescending { it.title }
                    }

                    is NoteOrder.Date -> {
                        notes.sortedByDescending { it.timeStamp }
                    }

                    is NoteOrder.Color -> {
                        notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}