package com.example.testingandroidapp.feature_note.presentation.note_list

import com.example.testingandroidapp.feature_note.domain.model.Note
import com.example.testingandroidapp.feature_note.domain.utils.NoteOrder
import com.example.testingandroidapp.feature_note.domain.utils.OrderType
import com.example.testingandroidapp.feature_note.redux.State

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible : Boolean = false,
    val recentlyDeletedNote: Note? = null
) : State
