package com.phillip_dev.knowtes.Db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.phillip_dev.knowtes.Model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object{
        private  var INSTANCE : NoteDatabase? = null
    }

}