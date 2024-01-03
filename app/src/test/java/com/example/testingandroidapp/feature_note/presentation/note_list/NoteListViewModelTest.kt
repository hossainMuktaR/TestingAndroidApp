package com.example.testingandroidapp.feature_note.presentation.note_list

import androidx.compose.ui.graphics.toArgb
import com.example.testingandroidapp.feature_note.data.repository.FakeNoteRepository
import com.example.testingandroidapp.feature_note.domain.model.Note
import com.example.testingandroidapp.feature_note.domain.use_case.AddNote
import com.example.testingandroidapp.feature_note.domain.use_case.DeleteNote
import com.example.testingandroidapp.feature_note.domain.use_case.GetAllNotes
import com.example.testingandroidapp.feature_note.domain.use_case.GetNoteById
import com.example.testingandroidapp.feature_note.domain.use_case.NoteUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NoteListViewModelTest {

    private lateinit var viewModel: NoteListViewModel
    val fakeRepository = FakeNoteRepository()
    val noteUseCases = NoteUseCases(
        getAllNotes = GetAllNotes(fakeRepository),
        addNote = AddNote(fakeRepository),
        deleteNote = DeleteNote(fakeRepository),
        getNoteById = GetNoteById(fakeRepository)

    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher= UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        viewModel = NoteListViewModel(
            useCases = noteUseCases
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun finish() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun toggleOrderSection_showSection_displayOrderSection () {
        viewModel.toggleOrderSection()
        val state = viewModel.state

        assertEquals(true, state.value.isOrderSectionVisible)

    }
    @Test
    fun deleteNote_checkDeletedSuccess() = runTest{
        val note =  Note(
            title = "smaple note title 1",
            content = "content",
            timeStamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
            id = 10
        )
        noteUseCases.addNote(note)
        viewModel.fetchOrderedList(viewModel.state.value.noteOrder, true)

        assertEquals( viewModel.state.value.notes.first().title, note.title)

        noteUseCases.deleteNote(note)
        viewModel.fetchOrderedList(viewModel.state.value.noteOrder, true)

        assertEquals(viewModel.state.value.notes, emptyList<Note>())

    }
}