package com.phillip_dev.knowtes.Db

import androidx.room.Dao
import androidx.room.Insert
import com.phillip_dev.knowtes.Model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Note)

}