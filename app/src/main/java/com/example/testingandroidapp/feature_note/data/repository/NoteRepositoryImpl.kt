package com.example.testingandroidapp.feature_note.data.repository

import com.example.testingandroidapp.feature_note.data.data_source.NoteDao
import com.example.testingandroidapp.feature_note.domain.model.Note
import com.example.testingandroidapp.feature_note.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes()

    override suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id)

    override suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    override suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

}