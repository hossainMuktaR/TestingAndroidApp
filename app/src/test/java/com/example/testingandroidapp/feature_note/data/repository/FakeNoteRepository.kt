package com.example.testingandroidapp.feature_note.data.repository

import com.example.testingandroidapp.feature_note.domain.model.Note
import com.example.testingandroidapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import java.util.LinkedList
import kotlin.random.Random

class FakeNoteRepository: NoteRepository {
    private val noteList = LinkedHashMap<Int, Note>()
    override suspend fun getAllNotes(): List<Note> = runBlocking{
        return@runBlocking noteList.map { it.value }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteList[id]
    }

    override suspend fun insertNote(note: Note) = runBlocking{
        noteList[note.id ?: Random.nextInt()] = note
    }

    override suspend fun deleteNote(note: Note): Unit = runBlocking{
        noteList.remove(note.id)
    }
}