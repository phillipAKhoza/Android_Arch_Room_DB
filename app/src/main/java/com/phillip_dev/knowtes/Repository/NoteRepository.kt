package com.phillip_dev.knowtes.Repository

import androidx.annotation.WorkerThread
import com.phillip_dev.knowtes.Db.NoteDao
import com.phillip_dev.knowtes.Model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private  val noteDao: NoteDao) {
        val allNotes: Flow<List<Note>> = noteDao.getAllNotes()

    @WorkerThread
    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    @WorkerThread
    suspend fun updateNote(note: Note){
        noteDao.update(note)
    }

}