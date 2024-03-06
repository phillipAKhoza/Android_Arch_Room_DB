package com.phillip_dev.knowtes.Db

import androidx.room.Database
import com.phillip_dev.knowtes.Model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase {
}