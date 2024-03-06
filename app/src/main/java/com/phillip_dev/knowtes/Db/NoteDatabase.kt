package com.phillip_dev.knowtes.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.phillip_dev.knowtes.Model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object{
        private  var INSTANCE : NoteDatabase? = null

        fun getDataBase(context: Context) : NoteDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                   context.applicationContext ,NoteDatabase::class.java,"note_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }

}