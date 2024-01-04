package com.example.testingandroidapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.graphics.toArgb
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.testingandroidapp.feature_note.data.data_source.NoteDatabase
import com.example.testingandroidapp.feature_note.domain.model.Note
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: NoteDatabase

    @Before
    fun setupDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).build()
    }
    @After
    fun closeDb() = database.close()

    @Test
    fun insertNoteAndGetById() = runTest {
        // GIVEN - Insert note
        val note =  Note(
            title = "smaple note title 1",
            content = "content",
            timeStamp = System.currentTimeMillis(),
            color = Note.noteColors.random().toArgb(),
            id = 10
        )
        database.noteDao.insertNote(note)

        // WHEN - Get the note by id
        val retriveNote = database.noteDao.getNoteById(note.id!!)

        // THEN - the loaded data contains the expected values
        assertThat(retriveNote as Note, notNullValue())
        assertThat(retriveNote.id, `is`(note.id!!))
        assertThat(retriveNote.title, `is`(note.title))
        assertThat(retriveNote.color, `is`(note.color))
    }
}
